# Promobit Contatos
Teste para dev da promobit

O app Promobit Contatos foi implementado em Kotlin nativo tendo Material Design como tema de layouts e usando Navigation Drawer como interface para a UI

1 - Tela Inicial Contatos 

A Tela Inicial foi implementada com um adapter para um listview que recebe os dados da solicitação GET diretamente do link da API fornecido e retorna os dados brutos que são modificados em “runtime” para atender ao método de chamada da activity correspondente, activity esta, derivada de uma classe Fragment.  
Foi criado um filtro para exibir somente os contatos com fotos reais e que é disparado quando a tela é iniciada.  
Foi utilizado a biblioteca PICASSO para fazer o Download das imagens.  
Foi utilizada a biblioteca siyamed:android-shape-imageview para fazer a foto aparecer redonda no view do adapter.

2 - Tela Incluir Novo Contato. 

A Tela Incluir Novo Contato foi criada para o usuário incluir contatos quando clicar no botão FAB, Nesta tela, o usuário informa os dados e clica no botão Salvar que faz uma solicitação POST para uma API própria e que salva os dados num database sqlite em um servidor após os mesmos terem sidos verificados se foram preenchidos corretamente.

3 - A API para salvar dados 

Foi criada de um script em PHP: "savecontacts.php", que recebe os dados informados no formulário da UI e os transmite como parâmetros enviados para o script da API via “url” que  os verifica e os recebe em uma instrução “REQUEST/POST” e os grava em um db sqlite, in memory, visto que, no estudo de caso foi informado ser desnecessário atualizar os dados na API.  
O referido arquivo está disponível na pasta root do git do projeto.


4 - Argumento para a arquitetura

Foi utilizada a arquitetura em questão, visto que, o template do Android Studio pode ser rapidamente modificado para atender ao layout proposto pelo estudo de caso, entretanto, o curto espaço de tempo não permitiu maior aproveitamento do mesmo, mas o projeto foi concluído e o aplicativo ficou completamente funcional dentro dos requisitos solicitados.
