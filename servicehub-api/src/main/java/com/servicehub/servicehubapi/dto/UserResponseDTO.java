package com.servicehub.servicehubapi.dto; // Define o pacote onde a classe UserResponseDTO está localizada.

import io.swagger.v3.oas.annotations.media.Schema; // Permite documentar a classe e seus campos no Swagger.

import java.time.LocalDateTime; // Permite trabalhar com data e hora.

@Schema(description = "Dados públicos do usuário") // Descreve no Swagger que esta classe contém dados públicos.
public class UserResponseDTO { // Declara a classe usada para enviar dados do usuário nas respostas da API.

    @Schema(description = "Identificador do usuário", example = "1") // Descreve o ID e mostra um exemplo no Swagger.
    private Long id; // Armazena o identificador do usuário.

    @Schema(description = "Nome completo do usuário", example = "João Felipe") // Descreve o nome e mostra um exemplo.
    private String fullName; // Armazena o nome completo do usuário.

    @Schema(description = "E-mail do usuário", example = "joao@email.com") // Descreve o e-mail e mostra um exemplo.
    private String email; // Armazena o e-mail do usuário.

    @Schema(description = "Telefone do usuário", example = "(62) 99999-9999") // Descreve o telefone e mostra um exemplo.
    private String phone; // Armazena o telefone do usuário.

    @Schema(description = "Biografia do usuário", example = "Desenvolvedor de software") // Descreve a biografia e mostra um exemplo.
    private String bio; // Armazena a biografia do usuário.

    @Schema(description = "URL da imagem de perfil", example = "https://site.com/avatar.jpg") // Descreve o endereço da imagem.
    private String avatarUrl; // Armazena a URL da imagem de perfil.

    @Schema(description = "Indica se o usuário está ativo", example = "true") // Descreve o status do usuário.
    private Boolean active; // Armazena true para ativo ou false para inativo.

    @Schema(description = "Data de criação do usuário") // Descreve a data de criação no Swagger.
    private LocalDateTime createdAt; // Armazena a data e hora de criação.

    @Schema(description = "Data da última atualização do usuário") // Descreve a data de atualização no Swagger.
    private LocalDateTime updatedAt; // Armazena a data e hora da última atualização.

    public UserResponseDTO() { // Cria um construtor vazio, necessário para algumas operações do Java e do Spring.
    } // Finaliza o construtor vazio.

    public UserResponseDTO( // Cria um construtor para preencher todos os dados do usuário.
                            Long id, // Recebe o identificador do usuário.
                            String fullName, // Recebe o nome completo.
                            String email, // Recebe o e-mail.
                            String phone, // Recebe o telefone.
                            String bio, // Recebe a biografia.
                            String avatarUrl, // Recebe a URL da imagem.
                            Boolean active, // Recebe o status do usuário.
                            LocalDateTime createdAt, // Recebe a data de criação.
                            LocalDateTime updatedAt // Recebe a data de atualização.
    ) {
        this.id = id; // Atribui o ID recebido ao atributo da classe.
        this.fullName = fullName; // Atribui o nome recebido ao atributo da classe.
        this.email = email; // Atribui o e-mail recebido ao atributo da classe.
        this.phone = phone; // Atribui o telefone recebido ao atributo da classe.
        this.bio = bio; // Atribui a biografia recebida ao atributo da classe.
        this.avatarUrl = avatarUrl; // Atribui a URL recebida ao atributo da classe.
        this.active = active; // Atribui o status recebido ao atributo da classe.
        this.createdAt = createdAt; // Atribui a data de criação recebida.
        this.updatedAt = updatedAt; // Atribui a data de atualização recebida.
    } // Finaliza o construtor completo.

    public Long getId() { // Cria um método para consultar o ID.
        return id; // Retorna o ID do usuário.
    } // Finaliza o método getId.

    public String getFullName() { // Cria um método para consultar o nome completo.
        return fullName; // Retorna o nome completo.
    } // Finaliza o método getFullName.

    public String getEmail() { // Cria um método para consultar o e-mail.
        return email; // Retorna o e-mail.
    } // Finaliza o método getEmail.

    public String getPhone() { // Cria um método para consultar o telefone.
        return phone; // Retorna o telefone.
    } // Finaliza o método getPhone.

    public String getBio() { // Cria um método para consultar a biografia.
        return bio; // Retorna a biografia.
    } // Finaliza o método getBio.

    public String getAvatarUrl() { // Cria um método para consultar a URL da imagem.
        return avatarUrl; // Retorna a URL da imagem.
    } // Finaliza o método getAvatarUrl.

    public Boolean getActive() { // Cria um método para consultar o status do usuário.
        return active; // Retorna true ou false.
    } // Finaliza o método getActive.

    public LocalDateTime getCreatedAt() { // Cria um método para consultar a data de criação.
        return createdAt; // Retorna a data de criação.
    } // Finaliza o método getCreatedAt.

    public LocalDateTime getUpdatedAt() { // Cria um método para consultar a data de atualização.
        return updatedAt; // Retorna a data de atualização.
    } // Finaliza o método getUpdatedAt.

} // Finaliza a classe UserResponseDTO.