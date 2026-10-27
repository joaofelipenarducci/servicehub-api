// Define o pacote onde esta classe está localizada.
package com.servicehub.servicehubapi.service;

// Importa a classe User, que representa o usuário.
import com.servicehub.servicehubapi.model.User;

// Importa o repositório responsável pelo acesso ao banco.
import com.servicehub.servicehubapi.repository.UserRepository;

// Importa a classe responsável pela sanitização de texto.
import com.servicehub.servicehubapi.security.TextSanitizer;

// Importa a anotação que identifica um serviço do Spring.
import org.springframework.stereotype.Service;

// Importa a interface para criptografar senhas.
import org.springframework.security.crypto.password.PasswordEncoder;

// Importa a classe List para trabalhar com listas.
import java.util.List;

// Importa Optional para representar um usuário que pode não existir.
import java.util.Optional;

// Indica ao Spring que esta classe contém regras de negócio.
@Service
public class UserService {

    // Declara o repositório de usuários.
    private final UserRepository userRepository;

    // Declara o codificador de senhas BCrypt.
    private final PasswordEncoder passwordEncoder;

    // Construtor que recebe as dependências necessárias.
    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        // Guarda o repositório recebido.
        this.userRepository = userRepository;

        // Guarda o codificador de senhas recebido.
        this.passwordEncoder = passwordEncoder;
    }

    // Salva um novo usuário com a senha criptografada.
    public User save(User user) {

        // Sanitiza a biografia, que é um campo de texto livre.
        user.setBio(TextSanitizer.sanitize(user.getBio()));

        // Criptografa a senha antes de armazená-la.
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        // Salva o usuário no banco de dados.
        return userRepository.save(user);
    }

    // Busca todos os usuários cadastrados.
    public List<User> findAll() {

        // Retorna a lista de usuários do banco.
        return userRepository.findAll();
    }

    // Busca um usuário pelo ID.
    public Optional<User> findById(Long id) {

        // Retorna o usuário encontrado ou Optional vazio.
        return userRepository.findById(id);
    }

    // Atualiza os dados de um usuário existente.
    public User update(Long id, User user) {

        // Busca o usuário existente pelo ID.
        User existingUser = userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Usuário não encontrado")
                );

        // Atualiza o nome.
        existingUser.setFullName(user.getFullName());

        // Atualiza o e-mail.
        existingUser.setEmail(user.getEmail());

        // Criptografa a nova senha antes de atualizar.
        existingUser.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        // Atualiza o telefone.
        existingUser.setPhone(user.getPhone());

        // Sanitiza a biografia antes de armazená-la.
        existingUser.setBio(TextSanitizer.sanitize(user.getBio()));

        // Atualiza a URL do avatar sem alterar seu conteúdo.
        existingUser.setAvatarUrl(user.getAvatarUrl());

        // Atualiza o status da conta.
        existingUser.setActive(user.getActive());

        // Salva as alterações no banco.
        return userRepository.save(existingUser);
    }

    // Exclui um usuário pelo ID.
    public void delete(Long id) {

        // Verifica se o usuário existe.
        if (!userRepository.existsById(id)) {

            // Informa que o usuário não foi encontrado.
            throw new RuntimeException("Usuário não encontrado");
        }

        // Exclui o usuário do banco de dados.
        userRepository.deleteById(id);
    }
}