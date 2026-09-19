// Define o pacote onde esta classe está localizada.
package com.servicehub.servicehubapi.service;

// Importa a classe User, que representa o usuário do sistema.
import com.servicehub.servicehubapi.model.User;

// Importa o UserRepository, responsável pelo acesso ao banco de dados.
import com.servicehub.servicehubapi.repository.UserRepository;

// Importa a anotação que identifica esta classe como um serviço do Spring.
import org.springframework.stereotype.Service;

// Importa a classe List, usada para trabalhar com listas de usuários.
import java.util.List;

// Importa a classe Optional, usada quando um usuário pode ou não ser encontrado.
import java.util.Optional;

// Informa ao Spring que esta classe possui regras de negócio da aplicação.
@Service
public class UserService {

    // Declara o repositório que será usado para acessar os usuários no banco.
    private final UserRepository userRepository;

    // Construtor da classe UserService.
    public UserService(UserRepository userRepository) {

        // Recebe o repositório e guarda ele no atributo da classe.
        this.userRepository = userRepository;
    }

    // Método responsável por salvar um novo usuário no banco de dados.
    public User save(User user) {

        // Envia o usuário recebido para o repositório salvar no banco.
        return userRepository.save(user);
    }

    // Método responsável por buscar todos os usuários cadastrados.
    public List<User> findAll() {

        // Busca todos os usuários no banco e retorna uma lista.
        return userRepository.findAll();
    }

    // Método responsável por buscar um usuário pelo seu ID.
    public Optional<User> findById(Long id) {

        // Procura no banco um usuário que possua o ID informado.
        // O Optional permite retornar um usuário ou informar que ele não existe.
        return userRepository.findById(id);
    }

    // Método responsável por atualizar um usuário existente.
    public User update(Long id, User user) {

        // Busca no banco o usuário que será atualizado.
        User existingUser = userRepository.findById(id)

                // Caso o usuário não seja encontrado, lança uma mensagem de erro.
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza o nome do usuário existente com o nome recebido na requisição.
        existingUser.setFullName(user.getFullName());

        // Atualiza o e-mail do usuário existente.
        existingUser.setEmail(user.getEmail());

        // Atualiza a senha do usuário existente.
        existingUser.setPasswordHash(user.getPasswordHash());

        // Atualiza o telefone do usuário existente.
        existingUser.setPhone(user.getPhone());

        // Atualiza a biografia do usuário existente.
        existingUser.setBio(user.getBio());

        // Atualiza o endereço da imagem do avatar do usuário existente.
        existingUser.setAvatarUrl(user.getAvatarUrl());

        // Atualiza o status do usuário, ativo ou inativo.
        existingUser.setActive(user.getActive());

        // Salva o usuário atualizado no banco de dados.
        // O createdAt original é preservado porque usamos o usuário existente.
        // O updatedAt será atualizado automaticamente pelo @UpdateTimestamp.
        return userRepository.save(existingUser);
    }

    // Método responsável por excluir um usuário pelo ID.
    public void delete(Long id) {

        // Verifica se existe um usuário com o ID informado.
        if (!userRepository.existsById(id)) {

            // Se o usuário não existir, lança uma exceção.
            throw new RuntimeException("Usuário não encontrado");
        }

        // Se o usuário existir, realiza a exclusão no banco.
        userRepository.deleteById(id);
    }
}