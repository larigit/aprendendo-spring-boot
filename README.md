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

