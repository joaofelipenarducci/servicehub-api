package com.servicehub.servicehubapi.controller; // Define o pacote onde o controller está localizado.

import com.servicehub.servicehubapi.dto.UserResponseDTO; // Importa o DTO de resposta do usuário.
import com.servicehub.servicehubapi.model.User; // Importa o modelo User.
import com.servicehub.servicehubapi.service.UserService; // Importa o serviço de usuários.

import io.swagger.v3.oas.annotations.Operation; // Permite documentar os endpoints no Swagger.
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Permite documentar os códigos de resposta.
import io.swagger.v3.oas.annotations.tags.Tag; // Permite agrupar os endpoints no Swagger.

import org.springframework.http.HttpStatus; // Importa os códigos HTTP.
import org.springframework.http.ResponseEntity; // Permite controlar o status e o corpo da resposta.
import org.springframework.web.bind.annotation.*; // Importa as anotações dos endpoints.

import java.util.List; // Permite trabalhar com listas.
import java.util.stream.Collectors; // Permite transformar listas.

@Tag(
        name = "Usuários", // Define o nome do grupo no Swagger.
        description = "Operações relacionadas aos usuários" // Descreve o grupo de endpoints.
)
@RestController // Indica que a classe é um controller que retorna dados, geralmente em JSON.
@RequestMapping("/api/users") // Define o endereço inicial dos endpoints.
public class UserController { // Declara a classe UserController.

    private final UserService userService; // Guarda o serviço responsável pelos usuários.

    public UserController(UserService userService) { // Construtor que recebe o UserService.
        this.userService = userService; // Guarda o serviço recebido.
    }

    // Criar usuário
    @Operation(
            summary = "Criar usuário", // Resumo do endpoint no Swagger.
            description = "Cadastra um novo usuário no sistema" // Descrição do endpoint.
    )
    @ApiResponse(
            responseCode = "201", // Informa que o cadastro retorna 201 Created.
            description = "Usuário cadastrado com sucesso" // Descreve o resultado.
    )
    @PostMapping // Define que o método responde a requisições POST.
    public ResponseEntity<UserResponseDTO> create(
            @RequestBody User user // Recebe os dados do usuário no corpo da requisição.
    ) {
        User savedUser = userService.save(user); // Salva o usuário no banco de dados.

        UserResponseDTO response = convertToDTO(savedUser); // Converte User para DTO.

        return ResponseEntity
                .status(HttpStatus.CREATED) // Define o status HTTP como 201 Created.
                .body(response); // Retorna o DTO no corpo da resposta.
    }

    // Listar todos os usuários
    @Operation(
            summary = "Listar usuários", // Resumo do endpoint no Swagger.
            description = "Retorna todos os usuários cadastrados" // Descrição do endpoint.
    )
    @ApiResponse(
            responseCode = "200", // Informa que a consulta retorna 200 OK.
            description = "Usuários encontrados com sucesso" // Descreve o resultado.
    )
    @GetMapping // Define que o método responde a requisições GET.
    public List<UserResponseDTO> findAll() { // Retorna uma lista de DTOs.
        return userService.findAll() // Busca todos os usuários no serviço.
                .stream() // Transforma a lista em um fluxo.
                .map(this::convertToDTO) // Converte cada User em DTO.
                .collect(Collectors.toList()); // Junta os DTOs em uma lista.
    }

    // Buscar usuário por ID
    @Operation(
            summary = "Buscar usuário por ID", // Resumo do endpoint no Swagger.
            description = "Retorna um usuário específico pelo seu ID" // Descrição do endpoint.
    )
    @ApiResponse(
            responseCode = "200", // Informa que usuário encontrado retorna 200 OK.
            description = "Usuário encontrado com sucesso" // Descreve o resultado.
    )

    @GetMapping("/{id}") // Define o endereço para buscar um usuário pelo ID.
    public ResponseEntity<UserResponseDTO> findById(
            @PathVariable Long id // Recebe o ID informado na URL.
    ) {
        return userService.findById(id) // Busca o usuário no serviço.
                .map(this::convertToDTO) // Converte o usuário encontrado para DTO.
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrar o usuário.
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 se não encontrar.
    }

    // Atualizar usuário
    @Operation(
            summary = "Atualizar usuário", // Resumo do endpoint no Swagger.
            description = "Atualiza os dados de um usuário existente" // Descrição do endpoint.
    )
    @ApiResponse(
            responseCode = "200", // Informa que a atualização retorna 200 OK.
            description = "Usuário atualizado com sucesso" // Descreve o resultado.
    )
    @PutMapping("/{id}") // Define que o método responde a requisições PUT.
    public UserResponseDTO update(
            @PathVariable Long id, // Recebe o ID do usuário.
            @RequestBody User user // Recebe os novos dados do usuário.
    ) {
        User updatedUser = userService.update(id, user); // Atualiza o usuário no banco.

        return convertToDTO(updatedUser); // Converte o usuário atualizado para DTO.
    }

    // Excluir usuário
    @Operation(
            summary = "Excluir usuário", // Resumo do endpoint no Swagger.
            description = "Remove um usuário pelo seu ID" // Descrição do endpoint.
    )
    @ApiResponse(
            responseCode = "204", // Informa que a exclusão retorna 204.
            description = "Usuário excluído com sucesso" // Descreve o sucesso.
    )
    @ApiResponse(
            responseCode = "404", // Informa que pode retornar 404.
            description = "Usuário não encontrado" // Descreve o erro.
    )
    @DeleteMapping("/{id}") // Define que o método responde a requisições DELETE.
    public ResponseEntity<Void> delete(
            @PathVariable Long id // Recebe o ID informado na URL.
    ) {

        // Verifica se o usuário não existe no banco de dados.
        if (userService.findById(id).isEmpty()) {

            // Retorna 404 caso o usuário não seja encontrado.
            return ResponseEntity.notFound().build();
        }

        // Exclui o usuário caso ele exista.
        userService.delete(id);

        // Retorna 204 indicando que a exclusão foi realizada.
        return ResponseEntity.noContent().build();
    }

    // Converte User para UserResponseDTO
    private UserResponseDTO convertToDTO(
            User user // Recebe o usuário completo.
    ) {
        return new UserResponseDTO( // Cria um DTO sem expor a senha.
                user.getId(), // Copia o ID.
                user.getFullName(), // Copia o nome completo.
                user.getEmail(), // Copia o e-mail.
                user.getPhone(), // Copia o telefone.
                user.getBio(), // Copia a biografia.
                user.getAvatarUrl(), // Copia a URL do avatar.
                user.getActive(), // Copia o status ativo.
                user.getCreatedAt(), // Copia a data de criação.
                user.getUpdatedAt() // Copia a data de atualização.
        );
    }
}