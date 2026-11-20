package com.EcoGuia.backend.controller;

import com.EcoGuia.backend.dto.AuthResponse;
import com.EcoGuia.backend.dto.LoginRequest;
import com.EcoGuia.backend.dto.RegisterRequest;
import com.EcoGuia.backend.model.Usuario;
import com.EcoGuia.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(request.getNombre()); // GUARDAMOS EL NOMBRE
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setToken(UUID.randomUUID().toString());

        usuarioRepository.save(nuevo);

        // Devolvemos un JSON con el token y el nombre
        return ResponseEntity.ok(new AuthResponse(nuevo.getToken(), nuevo.getNombre()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Correo o contraseña incorrectos.");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.badRequest().body("Correo o contraseña incorrectos.");
        }

        String nuevoToken = UUID.randomUUID().toString();
        usuario.setToken(nuevoToken);
        usuarioRepository.save(usuario);

        // Devolvemos un JSON con el token y el nombre
        return ResponseEntity.ok(new AuthResponse(usuario.getToken(), usuario.getNombre()));
    }
}