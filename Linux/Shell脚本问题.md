# 1. 什么是Shell？

1. Shell 是一个**命令行解释器**，它接受应用程序/用户命令然后调用操作系统内核。

2. Shell 还是一个功能相当强大的**编程语言**，易编写，易调试，灵活性强。

3. 执行方式

   （1）bash/sh/. /source + 文件路径(前两种是开了一个子shell，后两种是在父shell中执行的)

   （2）采用输入脚本的绝对路径或相对路径执行脚本（必须具有可执行权限+x）

# 2. 你用过shell里面的什么内容？

## 1. if then

```shell
#!/bin/bash
if [ $1 -eq 1 ]
then
        echo "the value is 1"
elif [ $1 -eq 2 ]
then
        echo "the value is 2"
elif [ $1 -eq 3 ]
then
        echo "the value is 3"
else
        echo "other value"
fi

```

## 2. case in

```shell
!/bin/bash
case $1 in
"1")
        echo "输入值是1"
;;
"2")
        echo "输入值是2"
;;
"3")
        echo "输入值是3"
;;
*)
        echo "其他值"
esac
```

## 3. for 和 for in

``` shell
!/bin/bash
for ((i=1; i<=5; i++))
do
        echo "当前值为$i"
done

echo '=============$*============='
for i in $*
do
        echo "循环参数: $i"
done

echo '=============$@============='
for j in $@
do
        echo "循环参数: $j"
done

echo '============="$*"============='
for i in "$*"
do
        echo "循环参数: $i"
done

echo '============="$@"============='
for j in "$@"
do
        echo "循环参数: $j"
done
```

## 4. while循环

``` shell
#!/bin/bash

sum=0
i=1

while [ $i -le 100 ]
do
        sum=$[$sum+$i]
        i=$[$i+1]
done

echo $sum
```

## 5. read读取控制台输入

``` shell
#!/bin/bash 


echo "适用read读取控制台输入"

read -t 7 -p "请在7秒之内输入参数值：" value

echo "您输入的参数值为$value"
```

## 6. funcation 函数

``` shell
#系统函数
#!/bin/bash

echo "系统函数basename：basename /home/atguigu/banzhang.txt"
basename /home/atguigu/banzhang.txt

echo "系统函数dirname：dirname /home/atguigu/banzhang.txt"
dirname /home/atguigu/banzhang.txt

#自定义函数
#!/bin/bash
function sum()
{
s=0
s=$[$1+$2]
echo "ans: $s"
return $s
}
read -p "Please input the number1: " n1;
read -p "Please input the number2: " n2;

sum $n1 $n2
result=$?
echo "最终的结果为1：$result"

result2=$(sum 11 22)
echo "最终的结果2为：$result2"
```

## 7. cut 进行切割

``` shell
cut -d " " -f 1 cut.txt ： 以 " " 分割 切割并获得第一列
```

## 8. awk 进行切割

https://blog.csdn.net/xiaojin21cen/article/details/117739791

## 9. 特殊变量

	（1）$n （功能描述：n为数字，$0代表该脚本名称，$1-$9代表第一到第九个参数，十以上的参数，十以上的参数需要用大括号包含，如${10}）
	（2）$#（功能描述：获取所有输入参数个数，常用于循环,判断参数的个数是否正确以及加强脚本的健壮性）
	（3）$* （功能描述：这个变量代表命令行中所有的参数，$*把所有的参数看成一个整体）
	（4）$@ （功能描述：这个变量也代表命令行中所有的参数，不过$@把每个参数区分对待）
	（5）$？（功能描述：最后一次执行的命令的返回状态。如果这个变量的值为0，证明上一个命令正确执行；如果这个变量的值为非0（具体是哪个数，由命令自己来决定），则证明上一个命令执行不正确了。）

# 3. 实际的shell语言的应用

## 1. 编写过hadoop集群的启动

``` shell
#!/bin/bash

if [ $# -lt 1 ]
then
    echo "No Args Input..."
    exit ;
fi

case $1 in
"start")
        echo " =================== 启动 hadoop集群 ==================="

        echo " --------------- 启动 hdfs ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/sbin/start-dfs.sh"
        echo " --------------- 启动 yarn ---------------"
        ssh hadoop103 "/opt/module/hadoop-3.1.3/sbin/start-yarn.sh"
        echo " --------------- 启动 historyserver ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/bin/mapred --daemon start historyserver"
;;
"stop")
        echo " =================== 关闭 hadoop集群 ==================="

        echo " --------------- 关闭 historyserver ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/bin/mapred --daemon stop historyserver"
        echo " --------------- 关闭 yarn ---------------"
        ssh hadoop103 "/opt/module/hadoop-3.1.3/sbin/stop-yarn.sh"
        echo " --------------- 关闭 hdfs ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/sbin/stop-dfs.sh"
;;
*)
    echo "Input Args Error..."
;;
esac
```

## 2. 编写ZK.集群的启动

``` shell
#!/bin/bash 
 case $1 in "start"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo ---------- zookeeper $i 启动 ------------ 
        ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh start"
 done
};; "stop"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo ---------- zookeeper $i 停止 ------------      
         ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh stop"
 done
};; "status"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo ---------- zookeeper $i 状态 ------------      
         ssh $i "/opt/module/zookeeper-3.5.7/bin/zkServer.sh status"
 done
};; esac
```

