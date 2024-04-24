# Desafio: CRUD de clientes
O desafio da vez foi entregar um projeto Spring Boot contendo um CRUD completo de web services REST para acessar um recurso de clientes, contendo cinco operações.

## Especificação - CRUD
As seguintes operações são feitas pelo sistema:
- Busca paginada de recursos.
- Busca de recursos por Id.
- Inserir um novo recurso.
- Atualizar recurso.
- Deletar recurso.

O projeto está configurado com um ambiente de teste acessando o banco de dados H2, usa Maven como gerenciador de dependência e Java como linguagem.

Um cliente possui nome, CPF, renda, data de nascimento e quantidade de filhos. A especificação da entidade ```Client``` é mostrada a seguir no diagrama:

![Diagrama de classe](/img/diagrama_classe.png)

**O projeto tem um seed de clientes**

**O projeto trata as seguintes exceções:**
- Id não encontrado (para GET por id, PUT e DELETE), retornando 404
- Erro de validação, retornando código 422 e mensagens customizadas para cada campo inválido. As regras de validação são: 
  - Nome não pode ser vazio e deve ter no mínimo 3 caracteres.
  - CPF não pode ser vazio e deve ser um CPF válido.
  - A renda deve ter um valor positivo.
  - Usuário não pode cadastrar um cliente com uma data de nascimento futura.

## Instruções de Uso
Antes de prosseguir com as instruções de uso, é requerido que você tenha em sua máquina o Java 21, git e Intellij.

### Instalação
Antes de tudo, recomendo que você, pelo terminal, navegue até o diretório Desktop e rode o comando:
```bash
# Clone o repositório
git clone https://github.com/LucasFrancoBN/desafio-crud-clientes.git
```
Agora basta você abrir o projeto na IDE Intellij, acessar o arquivo ```DesafioCrudClientesApplication``` e rodar o projeto.

