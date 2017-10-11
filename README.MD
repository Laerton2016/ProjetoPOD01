Projeto de POD


Conhecimentos necessários:
	Para implementar este projeto será necessário conhecimentos de:
outras linguagem (diferente de Java);
Transações Distribuídas;
Garantia de Entrega;
Publish/Subscribe Pattern;
RMI;
Estilo Arquitetural Baseado em Dados
Prazo de Entrega: 14/10/2017



Funcionalidades
Código
Nome
Descrição
Observações
F001
Registro de Usuário
Como visitante eu preciso registrar o meu nome e meu email para ter acesso ao chat.


F002
Solicitar Acesso
Como visitante eu preciso fazer login para ter acesso ao chat


F003
Inscrever em grupos
Como usuário eu preciso visualizar uma lista de grupos para me inscrever nele (considere um lista de 3 grupos existentes)


F004
Receber Mensagens
Como usuário inscrito em um determinado grupo eu preciso receber as mensagens enviadas por usuários deste grupo para que eu possa fazer a leitura destas mensagens.


F005
Enviar Mensagens
Como usuário inscrito em um determinado grupo eu preciso enviar uma mensagem para esse grupo para que os demais usuários desse grupo possam ler


F006
Logout
Como usuário eu preciso fazer logout na aplicação.




Visão Arquitetural






Componentes
Responsabilidades
Observações
chat-client
Realizar a comunicação entre clientes via inscrição em grupos de chats
 
server
Realizar a comunicação entre o chat-client e o app-data para atualização de dados. Responsável por criar tópicos de grupos para notificação de usuários. 

nota: não enviar uma mensagem mais de uma vez para um mesmo usuário


app-data
Responsável exclusivamente pela persistência de dados nos diversos meios de comunicação.
Utilizar a técnica de 2 Phase-Commit para garantir a consistência dos dados.




Especificações dos Gerenciadores dos Dados
Gerenciados de Dados


TXT
O gerenciador de dados em TXT é responsável por criar e excluir arquivos de notificação de usuários. O arquivo deverá:
ser criado com o nome do arquivo igual ao token de notificação do usuário;
conter os seguintes dados:
grupo,
usuário que enviou a msg,
a data/hora do envio e
o texto da mensagem
ser apagado depois que confirmado a entrega das mensagens para o usuário de destino.

Nota: utilizar o TXT no mesmo host do app-data
Google Drive e DropBox
O gerenciador de dados do GoogleDrive deverá armazenar dados de mensagens e usuários.

O gerenciador de dados do DropBox deverá armazenar dados de notificações (conforme estrutura de dados em TXT).

Uma mensagem deve conter uma identificação única, um identificador do usuário que enviou a mensagem, a data/hora do envio, o texto da mensagem e um identificador do grupo para o qual a mensagem foi enviada.

Um usuário deve conter uma identificação única, um nome e um email.


Requisitos de Qualidade do Sistema
Código - Nome
Descrição
Teste(visão geral)
RQ01 - Envio de Mensagens
Offline
Um usuário deve ter a possibilidade de enviar uma mensagem mesmo que não esteja conectado com o servidor, sendo garantido a entrega desta mensagem ao servidor quando houver conexão.
Enviar uma mensagem desconectado;
Conectar;
Verificar se a mensagem foi enviada

RQ02 - Receber mensagens de outros usuários enquanto estava offline
Um usuário deve ter a possibilidade de receber todas as mensagens enviadas para os grupos para os quais está inscrito, mesmo as que foram enviadas quando estava offline.
Enviar uma mensagem para o grupo com um usuário A conectado e usuário B desconectado;
Conectar B;
Verificar se a mensagem foi recebida por B

RQ03 - Garantir a consistência entre os dados do TXT e do DropBox 
Após a entrega das notificações aos usuários, no DropBox, as notificações devem mudar de estado (de pendente para entregue)
Enviar uma mensagem para o grupo com um usuário A conectado e usuário B desconectado;
Conectar B;
Verificar se a mensagem foi recebida por B;
Se sim, verificar se a notificação no DropBox mudou de estado.

RQ04 - Garantir a consistência entre os dados do DropBox e do GoogleDrive 
Todas as mensagens devem ser consistentes quanto a sua existência tanto no GoogleDrive quanto no DropBox.
Verificar se todas as mensagens existentes em GoogleDrive correspondem em formato e dados ao do DropBox.



Tipos de Projetos
Todos os projetos devem ser do tipo Maven;
É possível a adoção de Docker, desde que não seja adotado como Composer
Adotar o plugin maven-assembly-plugin para gerar o jar-executável (link https://maven.apache.org/plugins/maven-assembly-plugin/usage.html)
Deixar o repositório de dados do GoogleDrive e do DropBox público, caso contrário se a aplicação não puder ser testado será desconsiderada.

