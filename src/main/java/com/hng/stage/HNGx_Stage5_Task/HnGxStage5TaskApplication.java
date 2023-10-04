package com.hng.stage.HNGx_Stage5_Task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
		info =
		@io.swagger.v3.oas.annotations.info.Info(
				description = "This app provides stores video files",
				title = "Video Backend App",
				version = "1.0",
				contact = @Contact(name = "Ifeanyichukwu Obeta",
						email = "angeloobeta@hotmail.com",
						url = "https://github.com/angeloobeta/https://github.com/angeloobeta/Hng_Task_5_videoUploaderTask")
		),
		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "DEV Server"
				),
				@Server(
						url = "https://hngtask5videouploadertask-production.up.railway.app",
						description = "PROD server"
				)
		}
)

@EnableAsync
public class HnGxStage5TaskApplication {
//	public class HnGxStage5TaskApplication {

		public static void main(String[] args) {
			SpringApplication.run(HnGxStage5TaskApplication.class, args);
		}

	}

