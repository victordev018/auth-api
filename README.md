# Authentication API

Aplicação backend simples com o foco na implementação de autenticação e autorização com Spring Security.

## Tecnologias utilizadas:

- Java
- Spring Security
- JWT
- Postregres SQL

## rotas da API:

```markdown
GET /product - recupera uma lista de produtos (usuário deve estar autenticado)

POST /product - salva um novo produto no banco de dados (somente para usuário ADMIN)

POST /auth/login - efetua login na aplicação

POST /auth/register - resgistra um novo usuário
```

## Autenticação:

A autenticação é feita a partir de tokens `JWT`. 
Para se autenticar e poder utilizar dos recursos da aplicação, faça uma requisição HTTP POST no endpoint __/auth/register__:

```json
  {
  	"login" : "your_username",
  	"password" : "your_password"
  }
```

Em caso de sucesso receberá um token, que poderá ser utilizado nas nas próximas requisições, como este:

```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InVzZXIyIiwiZXhwIjoxNzMxOTQ2NDc3fQ.fA_pfETMW3eSWeiEUPn-E3N5LEkW-P65T7YCH1qgi5U"
}
```

> Obs: Este teoken possui um tempo de expiração, então quando expirado você precisará efetuar o login novamente.

## Autorização:

A autorização é baseada em `ROLES` (PAPÉIS), e nesta aplicação possuímos os seguinte papéis:

- USER - Para usuários comuns com permissões para acessar os produtos.
- ADMIN - Usuário possui as mesmas permissões de USER e ainda pode cadastrar novos produtos.

A role é definida no momento do cadastro, feito em uma requisição HTTP POST no endpoint __/auth/register__:

```json
  {
  	"login" : "your_username",
  	"password" : "your_password",
    "role" : "USER"
  }
```

## Como usar o token para acessar os recursos da aplicação:

Para acessar os recursos da aplicação, é necessário utilizar um __token de autenticação__. Em uma __API REST__, que segue uma arquitetura `STATELESS` (sem estado), 
o servidor não armazena a sessão do cliente. Portanto, o usuário deve incluir o token de autenticação em cada requisição enviada à API. Dessa forma, o Spring 
Security será capaz de verificar as permissões do usuário, autorizando ou negando o acesso ao recurso solicitado com base nas permissões do token.

O token de autenticação deve ser incluído no cabeçalho `Authorization` da requisição.

## Aprendizados:

Durante o desenvolvimento, aprendi a implementar um sistema de autenticação completo utilizando o __Spring Security__. Com isso, adquiri conhecimento sobre o 
uso de __tokens JWT__ (JSON Web Token) para autenticação segura, além de entender como gerenciar permissões de usuário por meio de __roles__. Essa experiência 
permitiu-me entender o fluxo por trás dessas etapas de segurança e dominar a configuração de autenticação e autorização, garantindo que os recursos da aplicação 
sejam acessados de forma controlada e segura.



