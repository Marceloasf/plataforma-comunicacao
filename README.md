# plataforma-comunicacao

Plataforma de comunicação com agendamento de mensagens. Back-end do projeto feito com Java 11 e Spring Framework e front-end feito com React (utilizando node 14).

### Scripts para que o banco postgresql execute da maneira esperada ao executar o back-end da applicação

CREATE DATABASE comunicacao_db;
CREATE USER comunicacao WITH ENCRYPTED PASSWORD 'comunicacao';
GRANT ALL PRIVILEGES ON DATABASE comunicacao_db TO comunicacao;
CREATE SCHEMA IF NOT EXISTS comunicacao AUTHORIZATION comunicacao;

### Para fazer as requisições ao back-end utilizando uma aplicação como o Postman:

Executar a classe `ComunicacaoApplication`, a qual irá inicializar o back-end da aplicação seguindo as properties definidas no arquivo application.yml. Após inicializar a aplicação, é possível utilizar as requisições citadas abaixo:

Para agendar uma nova comunicação, utilizar esse endpoint: POST: localhost:8080/api/comunicacoes

	Exemplo de JSON para POST: 
	{
		"dataEnvio": "2021-01-01T10:00:00",
		"destinatario": "fulano@gmail.com",
		"mensagem": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
		"tipo": "EMAIL",
		"status": "AGENDADA"
	}

> *Nota:* Esse endpoint espera que o objeto vindo da requisição possua apenas os campos definidos como nesse exemplo acima (dataEnvio, destinatario, mensagem, tipo e status).  

Para consultar o status de uma comunicação existente, utilizar esse endpoint: GET: localhost:8080/api/comunicacoes/*id*/status (substituir o *id* no path por algum criado no agendamento, o id do agendamento é criado sequencialmente)

Para excluir um agendamento existente, utilizar esse endpoint: DELETE: localhost:8080/api/comunicacoes/*id* (substituir o *id* da mesma maneira que o endpoint anterior)