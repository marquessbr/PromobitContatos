# Promobit Contatos
Teste para dev da promobit

O app Promobit Contatos foi implementado usando Material Design como solicitado em Kotlin nativo e usando Navigation Drawer como interface

1 - Tela Inicial Contatos
Foi implementado uma tela de contatos cuja origem provem de uma solicitacao get diretamente no link da API fornecido e retornando os dados brutos e modificados em runtime para atender ao metodo de chamada da activity correspondente, activity esta, derivada de uma classe Fragment
Foi criado um filtro para exibir somente os contatos com fotos reais

2 - Tela Incluir Novo Contato
Foi criado uma tela para o usuario incluir contatos quando clicar no botao FAB que faz uma solicitacoa POST para uma API propria e que salva os dados num dabase sqlite em um servidor.

3 - A API para salvar dados Foi criada de um script em PHP que recebe os dados informados no formulário pertinente passando-os como parâmetros REQUEST/POST para a API e são gravados em um db salite in memory visto o mesmo ter sido informado no referido estudo da não necessidade de atualizar dados na API
O referido arquivo esta disponivel na pasta root do git do projeto.
