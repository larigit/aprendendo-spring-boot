# Aprendendo Spring Boot
#### Aqui vou estar documentando conceitos novos que estou aprendendo com o curso de Spring Boot da Alura.

---

## @Controller:


![](controller.png) 


Sua função é ser uma camada intermediária entre a camada de apresentação (View) e a camada de negócios (Model).
O controlador serve como um intermediário que organiza os eventos da interface com usuário e os direciona para a camada de modelo, assim, torna-se possível um reaproveitamento da camada de modelo em outros sistemas já que não existe dependência entre a visualização e o modelo.
Esta é uma classe de controlador (controller) simples que contém métodos para manipular solicitações HTTP para diferentes URLs.


Links para saber mais: 
- [Diferença entre @RestController e @Controller Annotation no Spring MVC e REST](https://medium.com/@gcbrandao/diferen%C3%A7a-entre-restcontroller-e-controller-annotation-no-spring-mvc-e-rest-8533998a93eb)
- [Top 10 Spring MVC and REST Annotations para programadores Java](https://medium.com/@gcbrandao/top-10-spring-mvc-and-rest-annotations-para-programadores-java-989735894f43)
- [Padrão MVC - Java Magazine](https://www.devmedia.com.br/padrao-mvc-java-magazine/21995#5)

## @RequestMapping

![](requestmapping.png) 

A classe Controller contém vários métodos manipuladores para manipular as diferentes solicitações HTTP, mas como o Spring mapeia uma solicitação específica para um método manipulador específico? Bem, isso é feito com a ajuda da annotation @RequestMapping. É uma annotation no nível do método que é colocada sobre um método.

## O que é persistencia de dados?

Podemos entender o que é persistência de dados como a garantia de que um dado foi salvo e que poderá ser recuperado quando necessário no futuro. Esse conceito existe na computação para referenciar o ato de salvar os dados. Mantendo o registro de dados processados pelos sistemas, e transformando-os em dados persistentes, geramos insumos para análises futuras que vão possibilitar a tomada de decisões estratégicas por empresas para alavancar seus negócios. O mais usual é utilizar um banco de dados, mas nada impede que sejam utilizadas outras ferramentas, como uma planilha no Excel, um arquivo de texto, etc. Tudo depende do propósito dos dados persistentes e de seu contexto.
No entanto, caso seja necessário fazer uma boa análise de dados, é interessante que os dados estejam armazenados em locais que te darão maior poder de recuperação e organização, por isso a recomendação de utilizar um bom banco de dados.

Uma dificuldade que temos de enfrentar é que, apesar da padronização do SQL, cada banco tem suas peculiaridades, com instruções próprias e tipos de colunas diferentes, o que pode tornar bastante trabalhoso migrar de um banco para outro, dado que sua aplicação já está específica para um determinado banco.

Visando diminuir essa dificuldade de transição entre os bancos de dados, temos a JPA. Ela é uma API baseada no conceito de ORM (Object-Relational Mapping ou Mapeamento Objeto-Relacional, onde as tabelas do banco de dados são representadas através de classes e os registros de cada tabela são representados como instâncias das classes correspondentes.) e funciona como uma camada de abstração entre a aplicação e o banco de dados, possibilitando uma maior independência entre os mesmos.

Com ORM, o desenvolvedor passa a usar, ao inves de comandos SQL, uma interface de programação, não sendo necessário uma correspondência direta entre tabelas do banco de dadoss e classes Java.

Para tal são necessários metadados para essa persistência. Esses metadados podem ser desenvolvidos utilizando-se arquivos XML, Java annotations ou ambos.

Com annotations, por exemplo, você poderá ter em sua classe indicadores de que aquela classe esta sofrendo persistência (Entity) ou de que seus métodos são colunas de uma determinada tabela , como observado abaixo, nesse exemplo:

![](exemploannotations.gif) 

Por default, o nome de uma tabela corresponde ao nome da classe, mas isto pode ser mudado com uso de @Table(name="MYNEWTABLE"). O mesmo acontece com as colunas, onde cada field sera mapeado com o nome de uma coluna. Tambem pode ser mudado utilizando-se @Column(name="mynewColumn”). O uso de @Id define que aquele  atributo está mapeado e corresponde à chave primária da tabela. Se usarmos juntamente a @GeneratedValue, teremos um valor gerado automaticamente.

E, se estamos falando de banco de dados, também teremos annotations para desmostrar o relacionamento, como @OneToOne,  @OneToMany, @ManyToOne e @ManyToMany.


Links para saber mais: 
- [Entenda o que é persistência de dados](https://www.javaavancado.com/entenda-o-que-e-persistencia-de-dados/)
- [Persistência de dados: tudo que você precisa saber sobre conceito, tipos e técnicas](https://www.take.net/blog/tecnologia/persistencia-de-dados/?unapproved=27597&moderation-hash=614738c1d4e3643a579952aa8c2ec3c8#comment-27597)
- [Persistindo dados em Java com JPA](https://www.devmedia.com.br/persistindo-dados-em-java-com-jpa/29797)
- [Persistência em Java com API JPA](http://www.linhadecodigo.com.br/artigo/2525/persistencia-em-java-com-api-jpa.aspx)
