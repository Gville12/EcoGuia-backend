package com.EcoGuia;

import com.EcoGuia.backend.model.Categoria;
import com.EcoGuia.backend.model.Residuo;
import com.EcoGuia.backend.repository.CategoriaRepository; // Si no lo tienes, créalo
import com.EcoGuia.backend.repository.ResiduoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcoGuiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoGuiaApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ResiduoRepository residuoRepo, CategoriaRepository catRepo) {
		return args -> {
			// Si la tabla está vacía, insertamos los datos de prueba
			if (residuoRepo.count() == 0) {

				// 1. Creamos las categorías CON SU color_hex (obligatorio)
				Categoria envases = new Categoria();
				envases.setNombre("Envases");
				envases.setColorHex("#FFF0B8"); // Amarillo
				catRepo.save(envases);

				Categoria papel = new Categoria();
				papel.setNombre("Papel");
				papel.setColorHex("#DCECFF"); // Azul
				catRepo.save(papel);

				Categoria vidrio = new Categoria();
				vidrio.setNombre("Vidrio");
				vidrio.setColorHex("#D9F0DF"); // Verde
				catRepo.save(vidrio);

				Categoria organico = new Categoria();
				organico.setNombre("Orgánico");
				organico.setColorHex("#EADFCF"); // Marrón
				catRepo.save(organico);

				Categoria especiales = new Categoria();
				especiales.setNombre("Especiales");
				especiales.setColorHex("#F8D9D5"); // Rojo
				catRepo.save(especiales);

				// 2. Creamos los residuos y los asociamos a sus categorías
				Residuo r1 = new Residuo("Botella de plástico", "Contenedor amarillo", "Vacía y aplasta la botella.");
				r1.setCategoria(envases);
				residuoRepo.save(r1);

				Residuo r2 = new Residuo("Caja de cartón", "Contenedor azul", "Pliega la caja para que ocupe menos.");
				r2.setCategoria(papel);
				residuoRepo.save(r2);

				Residuo r3 = new Residuo("Tarro de vidrio", "Contenedor verde", "Retira la tapa y deposita el tarro.");
				r3.setCategoria(vidrio);
				residuoRepo.save(r3);

				Residuo r4 = new Residuo("Restos de fruta", "Contenedor marrón", "Usa bolsas compostables.");
				r4.setCategoria(organico);
				residuoRepo.save(r4);

				Residuo r5 = new Residuo("Pilas usadas", "Punto de recogida", "Nunca las tires a la basura normal.");
				r5.setCategoria(especiales);
				residuoRepo.save(r5);

				Residuo r6 = new Residuo("Lata de conserva", "Contenedor amarillo", "Vacía la lata antes de tirarla.");
				r6.setCategoria(envases);
				residuoRepo.save(r6);

				System.out.println("✅ ¡Datos de prueba insertados correctamente en la base de datos!");
			}
		};
	}
}