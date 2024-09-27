# Sistema de Gestão de Frota de Veículos

Este projeto consiste em um sistema de gestão de frotas que visa facilitar o gerenciamento de veículos, motoristas e viagens para uma empresa de transporte. O sistema permite o cadastro de diferentes tipos de veículos (como ônibus e caminhões), motoristas com suas respectivas habilitações, além de agendar e gerenciar viagens de acordo com a disponibilidade dos recursos.

## Funcionalidades Principais

O sistema implementa uma série de funcionalidades que facilitam a administração da frota e o agendamento de viagens, garantindo que a empresa possa gerenciar seu transporte de forma eficiente e sem conflitos.

### 1. Cadastro de Veículos
O sistema suporta o cadastro de diferentes tipos de veículos, como:

- **Ônibus**: Inclui informações como número de assentos, capacidade e tipo de combustível.
- **Caminhões**: Permite especificar a capacidade de carga e o número de eixos.

Cada veículo é categorizado conforme suas especificações, com informações como marca, modelo, ano de fabricação, capacidade, custo por dia de aluguel, e tipo de combustível (gasolina, diesel, etc.).

### 2. Gerenciamento de Frota
A frota de veículos pode ser gerenciada através de operações como:

- **Listagem de veículos**: Exibe todos os veículos cadastrados, agrupados por tipo (ônibus, caminhões, etc.).
- **Remoção de veículos**: Permite a remoção de um veículo da frota, desde que não esteja associado a uma viagem em andamento ou agendada.
- **Busca por veículos disponíveis**: Verifica quais veículos estão disponíveis para uso, de acordo com sua categoria e disponibilidade no sistema.

### 3. Cadastro e Gerenciamento de Motoristas
Os motoristas podem ser cadastrados no sistema com suas informações pessoais e sua categoria de habilitação (CNH). A gestão de motoristas inclui:

- **Verificação de disponibilidade**: Ao agendar uma viagem, o sistema verifica a disponibilidade do motorista.
- **Categorias de CNH**: Atribui aos motoristas a categoria de CNH correspondente ao tipo de veículo que podem conduzir.

### 4. Agendamento de Viagens
A principal funcionalidade do sistema é o agendamento de viagens. O sistema permite:

- **Agendamento de uma nova viagem**: Associa um veículo e um motorista disponíveis e cria uma nova viagem, determinando a data de partida e o destino.
- **Controle de disponibilidade de veículos e motoristas**: Um motorista ou veículo não pode ser utilizado em mais de uma viagem simultaneamente.
- **Cancelamento e Conclusão de Viagens**: O sistema permite o cancelamento de viagens que ainda não foram iniciadas e a conclusão de viagens em andamento.
- **Cálculo de custo**: O custo da viagem é calculado automaticamente com base na quantidade de dias da viagem e o custo diário de uso do veículo.

### 5. Tratamento de Exceções Customizadas
O sistema conta com um robusto tratamento de exceções, garantindo que o usuário seja informado de possíveis erros durante a execução das operações. Algumas das exceções personalizadas incluem:

- **DataInvalidaException**: Lançada quando uma data fornecida é inválida ou está fora do período permitido.
- **MotoristaIndisponivelException**: Lançada quando o motorista selecionado já está associado a outra viagem no mesmo período.
- **VeiculoIndisponivelException**: Lançada quando o veículo escolhido não está disponível para o período desejado.
- **ViagemInexistenteException**: Lançada ao tentar acessar ou manipular uma viagem que não existe no sistema.

## Estrutura do Projeto

O sistema foi estruturado em pacotes separados para garantir organização e modularidade, facilitando futuras manutenções e expansões do projeto.

### Pacotes

- **application**: Contém a classe principal para inicializar e executar o sistema.
  - `App.java`: Classe que contém o ponto de entrada (`main`) do sistema e gerencia a interação entre os componentes.

- **model**: Contém as entidades principais do sistema, bem como as exceções e enumerações.
  - **Veiculo.java**: Classe abstrata que define os atributos comuns de todos os veículos, como placa, marca, modelo, capacidade, e combustível.
  - **Onibus.java**: Herda de `Veiculo` e adiciona a propriedade de número de assentos.
  - **Caminhao.java**: Herda de `Veiculo` e adiciona a capacidade de carga e o número de eixos.
  - **Motorista.java**: Contém as informações dos motoristas, incluindo nome, CNH e categoria.
  - **Viagem.java**: Representa uma viagem, contendo o veículo e motorista associados, além das datas de início e fim.

- **utils**: Utilitários usados para facilitar a implementação de determinadas funcionalidades, como leitura de dados.
  - `DataUtils.java`: Contém métodos para manipulação de datas no sistema.

### Diagrama de Classes

O diagrama de classes a seguir apresenta a estrutura básica do sistema:

