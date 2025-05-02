package cl.ntt.usercreation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.ntt.usercreation.dto.ErrorResponseDTO;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                                .components(new Components()
                                                .addSecuritySchemes("bearerAuth",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT"))
                                                .addResponses("400", new ApiResponse()
                                                                .description("Error en la solicitud")
                                                                .content(new Content().addMediaType("application/json",
                                                                                new MediaType().schema(
                                                                                                new Schema<ErrorResponseDTO>()
                                                                                                                .example(new ErrorResponseDTO(
                                                                                                                                "Correo no tiene formato válido"))))))
                                                .addResponses("401", new ApiResponse()
                                                                .description("No autorizado")
                                                                .content(new Content().addMediaType("application/json",
                                                                                new MediaType().schema(
                                                                                                new Schema<ErrorResponseDTO>()
                                                                                                                .example(new ErrorResponseDTO(
                                                                                                                                "Token inválido"))))))
                                                .addResponses("404", new ApiResponse()
                                                                .description("Usuario no encontrado")
                                                                .content(new Content().addMediaType("application/json",
                                                                                new MediaType().schema(
                                                                                                new Schema<ErrorResponseDTO>()
                                                                                                                .example(new ErrorResponseDTO(
                                                                                                                                "Usuario no encontrado"))))))
                                                .addResponses("500", new ApiResponse()
                                                                .description("Error interno del servidor")
                                                                .content(new Content().addMediaType("application/json",
                                                                                new MediaType().schema(
                                                                                                new Schema<ErrorResponseDTO>()
                                                                                                                .example(new ErrorResponseDTO(
                                                                                                                                "Ocurrió un error inesperado, favor intente más tarde")))))))
                                .info(new Info()
                                                .title("API de Usuarios")
                                                .version("1.0")
                                                .description("Documentación de la API para la gestión de usuarios"));
        }
}
