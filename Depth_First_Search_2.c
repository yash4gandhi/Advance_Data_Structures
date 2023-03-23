/*
 NAME-YASH GANDHI
 SE IT
 BATCH A
 ROLL  NO-14
 
 QUESTION-DFS IMPLEMENTATION TO A GIVEN AADJACENCY MATRIX And show the time of when a node is visited 
*/ 
#include<stdio.h>
#include<stdlib.h>

int parent[7];  
int level[7];
int time[7];
int time2[7];
int visit[7];

int c=0;
void dfs(int a[][7],int s) //dfs function
{
    
  if(visit[s]==0)
  {
   visit[s]=1;
     c=c+1;
      time[s]=c;
  }  
  
  int i=s,j;
  for(j=0;j<7;j++)
  {
   if(a[i][j]==1)
   {
    int v=j;
    if(visit[v]==0)
     { 
      level[v]=level[s]+1;
     
      parent[v]=s;
      dfs(a,v);   //dfs function recursive call
     }
    }
  }
  
  if(visit[s]==1)
    {
     c=c+1;
    time2[s]=c;
    }
  return;
}
      
      
void print()
{
 int i;
 printf("\n\tvisit\tparent\tlevel\ttime1\ttime2\n");
 for(i=0;i<7;i++)
 {
  printf("%d\t%d\t%d\t%d\t%d\t%d\n",i,visit[i],parent[i],level[i],time[i],time2[i]);      
 }
}
   
void main()
{
 int a[7][7]={
               {0,1,0,1,0,0,0},
               {1,0,0,0,0,0,0},
               {0,0,0,1,1,0,0},
               {0,0,1,0,0,1,0},
               {1,1,0,0,0,0,1},
               {0,0,0,1,0,0,0},
               {0,0,0,0,1,1,0},
             };
             int i,j;
  printf("The adjacency matrix:-\n\n");           
  printf("\t0\t1\t2\t3\t4\t5\t6\n\n");           
  for(i=0;i<7;i++)
  {
    printf("%d\t",i);
    for(j=0;j<7;j++)
    {
     printf("%d\t",a[i][j]);
    }
    printf("\n");
  }
  
  for(i=0;i<7;i++) 
  {
   parent[i]=-1;
   level[i]=0;
   time[i]=0;
   visit[i]=0;
   time2[i]=0;
  }
    
    int s;
   printf("\nPlease enter source\n");
   scanf("%d",&s);
     printf("\nafter dfs\n");
  dfs(a,s);      //dfs function call       
  print(); 
}  
 
/*
OUTPUT-
spit@DB-Lab-406-U16:~$ cd Desktop
spit@DB-Lab-406-U16:~/Desktop$ gcc mse.c
spit@DB-Lab-406-U16:~/Desktop$ ./a.out
The adjacency matrix:-

	0	1	2	3	4	5	6

0	0	1	0	1	0	0	0	
1	1	0	0	0	0	0	0	
2	0	0	0	1	1	0	0	
3	0	0	1	0	0	1	0	
4	1	1	0	0	0	0	1	
5	0	0	0	1	0	0	0	
6	0	0	0	0	1	1	0	

Please enter source
6

after dfs

	visit	parent	level	time1	time2
0	1	4	2	3	12
1	1	0	3	4	5
2	1	3	4	7	8
3	1	0	3	6	11
4	1	6	1	2	13
5	1	3	4	9	10
6	1	-1	0	1	14
spit@DB-Lab-406-U16:~/Desktop$ 



*/  
  
