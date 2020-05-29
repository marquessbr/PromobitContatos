# Promobit Contatos
Teste para dev da promobit

O app Promobit Contatos foi implementado usando Material Design como solicitado em Kotlin nativo e usando Navigation Drawer como interface

1 - Tela Inicial Contatos  

A Tela Inicial foi implementada com um adapter de um listview que recebe os dados da solicitacao GET diretamente no link da API fornecido e retornando os dados brutos e modificados em runtime para atender ao metodo de chamada da activity correspondente, activity esta, derivada de uma classe Fragment
Foi criado um filtro para exibir somente os contatos com fotos reais
Foi utilizado a bilioteca PICASSO para fazer o Deownload das imagens
Foi utilizada a biblioteca siyamed:android-shape-imageview para fazer a foto aparcer circular no adapter

2 - Tela Incluir Novo Contato. 

A Tela Incluir Novo Contato foi criada para o usuario incluir contatos quando clicar no botao FAB
Nesta tela, o usuario informa os dados e clica no botao Salvar que faz uma solicitacoa POST para uma API propria e que salva os dados num dabase sqlite em um servidor.

3 - A API para salvar dados 

Foi criada de um script em PHP que recebe os dados informados no formulário pertinente passando-os como parâmetros REQUEST/POST para a API e são gravados em um db salite in memory visto o mesmo ter sido informado no referido estudo da não necessidade de atualizar dados na API
O referido arquivo esta disponivel na pasta root do git do projeto.
