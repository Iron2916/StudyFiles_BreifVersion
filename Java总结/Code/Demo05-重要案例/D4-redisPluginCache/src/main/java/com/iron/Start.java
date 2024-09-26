package com.iron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@SpringBootApplication
public class Start
{
    public static void main(String[] args)
    {
        SpringApplication.run(Start.class, args);

        String var = "#userid";

        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(var);

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("userid","1253");

        String s1 = expression.getExpressionString();
        String s2 = expression.getValue(context).toString();

        System.out.println(s1 + ":" + s2);
    }
}
