# testeXbrain
teste prático Xbrain

Impedimentos: Tive impedimentos para desenvolver os testes unitários para o projeto (fiz apenas para o cadastro e edição de vendedores) por conta do tempo. Por conta da faculdade consegui iniciar o projeto apenas no dia 25/11, e no dia 01/12 e 02/12 estudei sobre testes unitários(mockito) e consegui desenvolver apenas uma parte.
Obs: acredito que a parte dos testes unitários que desenvolvi estão corretos, mas ainda estou com algumas dificuldades e já irei estudar mais para dominar o assunto.

Também estou ciente que meu código está fora dos padrões da empresa, como o uso do ModelMapper ao inves do builder(lombook), a não utilização da variável var e entre outros pontos que acabam deixando o meu código mais poluido, mas já estou estudando para começar a desenvolver nos padrões da empresa.

---

*ENDPOINTS*

Vendedor:
- POST http://localhost:9999/vendedores : Cadastro ou edição de vendedores (Passando id no body da requisição irá editar um vendedor, sem id irá cadastrar um vendedor)
- GET http://localhost:9999/vendedores : retorna todos os vendedores
- GET http://localhost:9999/vendedores/{id} : retorna vendedor pelo id
- GET http://localhost:9999/vendedores/listaVendedores?dataInicial={dataInicial}&dataFinal={dataFinal} : retorna todos os vendedores total de vendas e média de vendas diária de acordo com o intervalo de datas
- GET http://localhost:9999/vendedores/listaVendedor/{vendedor_id}?dataInicial={dataInicial}&dataFinal={dataFinal} : retorna vendedor com seu total de vendas e média de vendas diária de acordo com o intervalo de datas e o id passado na url

Exemplo: http://localhost:9999/vendedores/listaVendedores?dataInicial=2022-11-27&dataFinal=2022-11-28 - as datas no parametro devem ter a formatação de yyyy-MM-dd

Vendas:
- POST http://localhost:9999/vendas/novaVenda/vendedorId={vendedorId} : criação de vendas (Passar id do vendedor na url e os dados da venda no body da requisição)
- GET http://localhost:9999/vendas : retorna todas as vendas
- GET http://localhost:9999/vendas/{id} : retorna vendas por id
- GET http://localhost:9999/vendas/findByDatas?dataInicial={dataInicial}&dataFinal={dataFinal} : retorna todas as vendas criadas em um intervalo de datas
