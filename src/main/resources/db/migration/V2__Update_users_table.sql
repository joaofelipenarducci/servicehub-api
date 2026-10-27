-- ============================================================
-- V2 - Atualização da tabela users
-- Projeto: ServiceHub API
-- Objetivo: adequar a tabela users ao schema de referência.
-- ============================================================

-- 1. Normaliza os e-mails para letras minúsculas.
UPDATE users
SET email = LOWER(email);

-- 2. Corrige registros sem status definido.
UPDATE users
SET is_active = TRUE
WHERE is_active IS NULL;

-- 3. Define is_active como obrigatório e mantém TRUE como padrão.
ALTER TABLE users
    ALTER COLUMN is_active SET DEFAULT TRUE;

ALTER TABLE users
    ALTER COLUMN is_active SET NOT NULL;

-- 4. Define a data de criação como obrigatória.
ALTER TABLE users
    ALTER COLUMN created_at SET DEFAULT NOW();

ALTER TABLE users
    ALTER COLUMN created_at SET NOT NULL;

-- 5. Define a data de atualização como obrigatória.
ALTER TABLE users
    ALTER COLUMN updated_at SET DEFAULT NOW();

ALTER TABLE users
    ALTER COLUMN updated_at SET NOT NULL;

-- ============================================================
-- Fim da migração V2
-- ============================================================