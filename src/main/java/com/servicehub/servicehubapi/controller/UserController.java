package com.servicehub.servicehubapi.controller; // Define o pacote do controller.

import com.servicehub.servicehubapi.dto.UserRequestDTO; // DTO de entrada.
import com.servicehub.servicehubapi.dto.UserResponseDTO; // DTO de resposta.
import com.servicehub.servicehubapi.model.User; // Modelo do usuário.
import com.servicehub.servicehubapi.service.UserService; // Serviço de usuários.

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid; // Valida os dados recebidos.

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Define o grupo de endpoints no Swagger.
@Tag(
        name = "Usuários",
        description = "Operações relacionadas aos usuários"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Guarda a referência ao serviço de usuários.
    private final UserService userService;

    // Recebe o serviço pelo construtor.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Cadastra um novo usuário.
    @Operation(
            summary = "Criar usuário",
            description = "Cadastra um novo usuário no sistema"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Usuário cadastrado com sucesso"
    )
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO userRequest
    ) {
        // Cria a entidade que será armazenada no banco.
        User user = new User();

        // Transfere os dados recebidos do DTO para a entidade.
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());

        // O serviço criptografará a senha antes de armazená-la.
        user.setPasswordHash(userRequest.getPassword());

        user.setPhone(userRequest.getPhone());
        user.setBio(userRequest.getBio());
        user.setAvatarUrl(userRequest.getAvatarUrl());

        // Define a conta como ativa no cadastro.
        user.setActive(true);

        // Salva o usuário no banco de dados.
        User savedUser = userService.save(user);

        // Converte o usuário salvo em DTO.
        UserResponseDTO response = convertToDTO(savedUser);

        // Retorna HTTP 201 (Created).
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Lista todos os usuários cadastrados.
    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuários encontrados com sucesso"
    )
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserResponseDTO> findAll() {

        // Busca os usuários e converte cada um em DTO.
        return userService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Busca um usuário específico pelo ID.
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico pelo seu ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> findById(
            @PathVariable Long id
    ) {
        // Busca o usuário pelo ID e converte o resultado em DTO.
        return userService.findById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualiza os dados de um usuário existente.
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário atualizado com sucesso"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequest
    ) {
        // Cria uma entidade com os novos dados recebidos.
        User user = new User();

        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());

        // O serviço criptografará a nova senha.
        user.setPasswordHash(userRequest.getPassword());

        user.setPhone(userRequest.getPhone());
        user.setBio(userRequest.getBio());
        user.setAvatarUrl(userRequest.getAvatarUrl());

        // Verifica se o usuário existe.
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Atualiza o usuário existente.
        User updatedUser = userService.update(id, user);

        // Retorna os dados atualizados sem expor a senha.
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    // Exclui um usuário pelo ID.
    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário pelo seu ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Usuário excluído com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        // Verifica se o usuário existe.
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Solicita a exclusão ao serviço.
        userService.delete(id);

        // Retorna HTTP 204, sem conteúdo na resposta.
        return ResponseEntity.noContent().build();
    }

    // Converte a entidade User em UserResponseDTO.
    private UserResponseDTO convertToDTO(User user) {

        // Seleciona apenas os dados que podem ser enviados na resposta.
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getBio(),
                user.getAvatarUrl(),
                user.getActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}