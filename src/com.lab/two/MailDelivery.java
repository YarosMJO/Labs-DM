package com.lab.two;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MailDelivery {

    final static int M = 10; //number of edges


    public static void main(String[] args) {

        boolean[] gasse = new boolean[M];
        Eji[] wvGraf = new Eji[M];

        try(FileReader reader = new FileReader("E:\\Laby\\Yaroslav\\src\\com.lab\\two\\lab2"))
        {
            int c;
            int numbOut=0, numbIn=0, i=0;
            while((c=reader.read())!=-1){

                if ((char)c=='\n') {
                    wvGraf[i]=new Eji(numbOut,numbIn);
                    i++;
                }
                else if (c >= 65) {
                    numbOut= Transformer.upperLetterToNum((char) c);
                    while((c = reader.read())<65);
                    numbIn= Transformer.upperLetterToNum((char) c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        System.out.print("Input place to start: ");
        String firstDot = in.next();

        if (!Walk(gasse,wvGraf, Transformer.upperLetterToNum(firstDot.charAt(0))))
            System.out.println("no way");
    }

    public static boolean fullWay(boolean[] gasse){
        for (int i = 0; i<gasse.length;i++){
            if(!gasse[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean Walk(boolean[] gasse, Eji[] graph, int gps)
    {
        if(fullWay(gasse))
            return true;
        else
        {
            for(int i=0;i<M;i++)
                if(!gasse[i] && (graph[i].v==gps||graph[i].u==gps))
                {
                    gasse[i]=true;
                    if(graph[i].v==gps)
                    {
                        Walk(gasse,graph,graph[i].u);
                        if(fullWay(gasse))
                        {
                            System.out.println(Transformer.numToUpperLetter(graph[i].u)+"-"+ Transformer.numToUpperLetter(graph[i].v));
                            return true;
                        }
                        else
                            gasse[i]=false;
                    }
                    else
                    {
                        Walk(gasse,graph,graph[i].v);
                        if(fullWay(gasse))
                        {
                            System.out.println(Transformer.numToUpperLetter(graph[i].v)+"-"+ Transformer.numToUpperLetter(graph[i].u));
                            return true;
                        }
                        else
                            gasse[i]=false;
                    }
                }
            return false;
        }
    }

    private static class Eji {
        int v;
        int u;
        public Eji(int v, int u){
            this.v=v;
            this.u=u;
        }
    }

    private static class Transformer {
        public static char numToUpperLetter(int num) {
            return (char)(num+65);
        }
        public static int upperLetterToNum(char s) {
            return (int)s-65;
        }
    }
}
