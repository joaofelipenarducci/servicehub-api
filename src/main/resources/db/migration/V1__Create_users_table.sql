-- Cria a tabela de usuários do ServiceHub.
CREATE TABLE users (
    -- Identificador único do usuário.
                       id BIGSERIAL PRIMARY KEY,

    -- Nome completo do usuário.
                       full_name VARCHAR(100) NOT NULL,

    -- E-mail único, armazenado em letras minúsculas.
                       email VARCHAR(255) NOT NULL UNIQUE,

    -- Hash da senha; nunca armazenar a senha em texto puro.
                       password_hash VARCHAR(255) NOT NULL,

    -- Papel do usuário na plataforma.
                       role VARCHAR(20) NOT NULL DEFAULT 'CLIENT',

    -- Telefone de contato.
                       phone VARCHAR(20),

    -- Biografia opcional.
                       bio TEXT,

    -- URL da imagem de perfil.
                       avatar_url VARCHAR(500),

    -- Define se a conta está ativa.
                       is_active BOOLEAN NOT NULL DEFAULT TRUE,

    -- Data e hora de criação do usuário.
                       created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    -- Data e hora da última atualização.
                       updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    -- Restringe os e-mails a letras minúsculas.
                       CONSTRAINT chk_users_email_low
                           CHECK (email = LOWER(email)),

    -- Restringe os papéis permitidos.
                       CONSTRAINT chk_users_role
                           CHECK (role IN ('ADMIN', 'PROVIDER', 'CLIENT'))
);

-- Cria um índice para facilitar buscas por e-mail.
CREATE INDEX idx_users_email ON users(email);