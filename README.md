# Desafio Sensedia

## Idealização

A princípio, esse desenho demonstra minha idealização de como gostaria que o desafio fosse implementado. Na imagem, temos três serviços, denominados com o prefixo `srv`, sendo eles: `srv-files-manager`, `srv-files-consumer-processing` e `srv-files-consumer-notify`.

- O primeiro serviço, **srv-files-manager**, é a porta de entrada e recebe a solicitação do usuário para o upload de um arquivo, juntamente com seu email. Ele é responsável por realizar o upload do arquivo para o S3, salvar os dados e publicar na fila `file-processing-queue`, assim finalizando seu ciclo de vida.

- O segundo serviço, **srv-files-consumer-processing**, consome a fila `file-processing-queue`. Este serviço é responsável por receber a mensagem da fila, buscar as informações referentes ao ID do processamento no DynamoDB, carregar o documento do S3 e, por fim, processar seu conteúdo. Ao término do processamento, ele publicará uma mensagem com o resultado no tópico SNS, que, por sua vez, enviará a mensagem para a fila de notificação.

- O terceiro serviço, **srv-files-consumer-notify**, consome a fila `file-processing-notify-queue`. Este serviço notificará o usuário, através do serviço SES, sobre o resultado do processamento.

## Descobertas no processo de desenvolvimento

Durante o processo de desenvolvimento, tive contato com a ferramenta **LocalStack**, que me permitiu simular a infraestrutura necessária para desenvolver e testar todo o meu ecossistema. Embora seja simples, em um cenário de produção, precisaríamos de várias outras configurações de segurança, como as **IAMs** para os serviços mencionados anteriormente.

## Preocupações e dúvidas

No desafio, fala-se sobre o processamento do conteúdo do arquivo e a comunicação entre sistemas. Em um cenário onde o conteúdo do arquivo possa exceder **256 KiB**, tamanho máximo recomendado pela **AWS** para transportar na fila, isso acarretaria lentidão no processo. 

Uma das soluções seria salvar o conteúdo no **DynamoDB** e enviar para a fila a `PartitionKey` do registro. No entanto, o que esperamos não é o conteúdo do arquivo, e sim o resultado do processamento. Por isso, no meu desenho incluí o upload do arquivo no **S3**, para que possamos posteriormente lê-lo, processá-lo e gravar apenas o resultado daquele arquivo.

