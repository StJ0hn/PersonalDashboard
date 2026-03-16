# UC01 — Criar Tarefa
- Ator: Usuário
- Pré condição: Sistema iniciado
- Pós condição: Tarefa criada e salva com Status Pendente
## Fluxo Principal
1. No **Menu Principal** o **Usuário** seleciona "Adicionar Tarefa"
2. Sistema solicita o **título** da tarefa
3. **Usuário** informa título
4. Sistema solicita **descrição** da atividade
5. Usuário insere a **descrição** da atividade
6. Sistema solicita **prioridade (Alta/Média/Baixa)**
7. Usuário informa **prioridade**
8. Sistema solicita uma **categoria**
9. Usuário informa a **categoria** da atividade
10. Sistema solicita a **data de expiração** da tarefa
11. Usuário informa data de expiração da tarefa
12. Sistema salva a tarefa e exibe confirmação de criação de tarefa, atribuindo o **status** da tarefa como: **pendente**
## Fluxo alternativo
- 1. Em caso do Usuário no passo 3 informar título em branco, Sistema deve exibir a mensagem: "Uma tarefa com título em branco não é permitida. Insira um título." e retornar para o passo 2.
- 2. No caso do Usuário informar uma categoria em branco, exibir a mensagem: "Uma tarefa deve possuir uma categoria. Insira uma categoria", em seguida o Sistema deve retornar ao passo 6.
---
# UC02 — Editar Tarefa
- Ator: Usuário
- Pré-condição: Haver Tarefas existentes com status classificado como "Pendente"
- Pós-condição: Tarefa Editada e salva
## Fluxo principal
1. Usuário seleciona "Editar Tarefa" no menu
2. Sistema solicita título ou parte do título da tarefa
3. Usuário informa o termo de busca
4. Sistema exibe lista de tarefas correspondentes
5. Usuário seleciona a tarefa desejada
6. Sistema exibe os campos editáveis
7. Usuário altera os campos desejados
8. Usuário confirma a edição
9. Sistema salva as alterações e exibe confirmação
## Fluxo alternativo
- 1. Caso a atividade pesquisada não seja encontrada, sistema deve exibir: "Tarefa não encontrada."
---
# UC03 — Concluir Tarefa
- Ator: Usuário
- Pré-condição: Haver tarefas com status "Pendente"
- Pós-condição: Tarefa salva com status "Concluída"

## Fluxo Principal
1. Usuário seleciona "Concluir Tarefa" no menu
2. Sistema solicita título ou parte do título da tarefa
3. Usuário informa o termo de busca
4. Sistema exibe lista de tarefas correspondentes
5. Usuário seleciona a tarefa desejada
6. Sistema altera o status da tarefa para "Concluída"
7. Sistema registra a data/hora de conclusão
8. Sistema salva e exibe confirmação

## Fluxo Alternativo
- 1. Tarefa pesquisada não encontrada → Sistema exibe: "Tarefa não encontrada."
- 2. Tarefa já possui status "Concluída" → Sistema exibe: "Essa tarefa já foi concluída."

---

# UC04 — Excluir Tarefa
- Ator: Usuário
- Pré-condição: Haver tarefas existentes
- Pós-condição: Tarefa removida do sistema

## Fluxo Principal
1. Usuário seleciona "Excluir Tarefa" no menu
2. Sistema solicita título ou parte do título da tarefa
3. Usuário informa o termo de busca
4. Sistema exibe lista de tarefas correspondentes
5. Usuário seleciona a tarefa desejada
6. Sistema solicita confirmação: "Deseja excluir a tarefa? (S/N)"
7. Usuário confirma
8. Sistema remove a tarefa e exibe confirmação

## Fluxo Alternativo
- 1. Tarefa pesquisada não encontrada → Sistema exibe: "Tarefa não encontrada."
- 2. Usuário cancela no passo 7 → Sistema retorna ao menu principal

---

# UC05 — Listar Tarefas
- Ator: Usuário
- Pré-condição: Sistema iniciado
- Pós-condição: Lista de tarefas exibida

## Fluxo Principal
1. Usuário seleciona "Listar Tarefas" no menu
2. Sistema exibe todas as tarefas com: título, prioridade, categoria, status e prazo

## Fluxo Alternativo
- 1. Não há tarefas cadastradas → Sistema exibe: "Nenhuma tarefa encontrada."

---

# UC06 — Filtrar Tarefas
- Ator: Usuário
- Pré-condição: Haver tarefas cadastradas
- Pós-condição: Lista filtrada exibida

## Fluxo Principal
1. Usuário seleciona "Filtrar Tarefas" no menu
2. Sistema exibe opções de filtro: Status, Prioridade, Categoria
3. Usuário seleciona o critério de filtro
4. Usuário informa o valor do filtro
5. Sistema exibe as tarefas correspondentes

## Fluxo Alternativo
- 1. Nenhuma tarefa corresponde ao filtro → Sistema exibe: "Nenhuma tarefa encontrada para o filtro informado."

---

# UC07 — Buscar Tarefa
- Ator: Usuário
- Pré-condição: Haver tarefas cadastradas
- Pós-condição: Tarefa encontrada exibida

## Fluxo Principal
1. Usuário seleciona "Buscar Tarefa" no menu
2. Sistema solicita título ou parte do título
3. Usuário informa o termo de busca
4. Sistema exibe as tarefas correspondentes

## Fluxo Alternativo
- 1. Nenhuma tarefa encontrada → Sistema exibe: "Tarefa não encontrada."

---

# UC08 — Criar Meta de Estudo
- Ator: Usuário
- Pré-condição: Sistema iniciado
- Pós-condição: Meta criada e salva

## Fluxo Principal
1. Usuário seleciona "Criar Meta" no menu
2. Sistema solicita título da meta
3. Usuário informa título
4. Sistema solicita área (ex: Java, Cloud)
5. Usuário informa área
6. Sistema solicita horas-alvo
7. Usuário informa horas-alvo
8. Sistema solicita prazo
9. Usuário informa prazo
10. Sistema salva a meta e exibe confirmação

## Fluxo Alternativo
- 1. Título em branco → Sistema exibe: "Uma meta deve possuir um título." e retorna ao passo 2
- 2. Horas-alvo inválidas (zero ou negativo) → Sistema exibe: "Informe um valor válido para as horas-alvo." e retorna ao passo 6

---

# UC09 — Registrar Sessão de Estudo
- Ator: Usuário
- Pré-condição: Haver metas cadastradas
- Pós-condição: Sessão registrada e progresso da meta atualizado

## Fluxo Principal
1. Usuário seleciona "Registrar Sessão" no menu
2. Sistema solicita título ou parte do título da meta
3. Usuário informa o termo de busca
4. Sistema exibe lista de metas correspondentes
5. Usuário seleciona a meta desejada
6. Sistema solicita duração da sessão em minutos
7. Usuário informa a duração
8. Sistema solicita uma anotação (opcional)
9. Usuário informa anotação ou deixa em branco
10. Sistema registra a sessão e atualiza o progresso da meta

## Fluxo Alternativo
- 1. Meta não encontrada → Sistema exibe: "Meta não encontrada."
- 2. Duração inválida (zero ou negativo) → Sistema exibe: "Informe uma duração válida." e retorna ao passo 6

---

# UC10 — Visualizar Progresso de Meta
- Ator: Usuário
- Pré-condição: Haver metas cadastradas
- Pós-condição: Progresso exibido

## Fluxo Principal
1. Usuário seleciona "Visualizar Progresso" no menu
2. Sistema solicita título ou parte do título da meta
3. Usuário informa o termo de busca
4. Sistema exibe: horas acumuladas, horas-alvo, percentual de progresso e histórico de sessões

## Fluxo Alternativo
- 1. Meta não encontrada → Sistema exibe: "Meta não encontrada."

---

# UC11 — Visualizar Dashboard
- Ator: Usuário
- Pré-condição: Sistema iniciado
- Pós-condição: Dashboard exibido

## Fluxo Principal
1. Usuário seleciona "Dashboard" no menu
2. Sistema exibe:
    - Total de tarefas pendentes, concluídas e atrasadas
    - Horas de estudo por área
    - Streak de dias consecutivos com estudo registrado

## Fluxo Alternativo
- 1. Sem dados cadastrados → Sistema exibe: "Nenhum dado disponível ainda."

---

# UC12 — Exportar CSV
- Ator: Usuário
- Pré-condição: Haver tarefas cadastradas
- Pós-condição: Arquivo CSV gerado

## Fluxo Principal
1. Usuário seleciona "Exportar CSV" no menu
2. Sistema solicita o nome do arquivo
3. Usuário informa o nome
4. Sistema gera o arquivo CSV com todas as tarefas
5. Sistema exibe confirmação com o caminho do arquivo gerado

## Fluxo Alternativo
- 1. Nome do arquivo em branco → Sistema usa nome padrão: "tarefas_export.csv"
- 2. Erro ao gerar arquivo → Sistema exibe: "Não foi possível exportar o arquivo."