## 1. MySQL 使用三值逻辑

MySQL 使用三值逻辑 —— TRUE, FALSE 和 UNKNOWN。任何与 NULL 值进行的比较都会与第三种值 UNKNOWN 做比较。这个“任何值”包括 NULL 本身！这就是为什么 MySQL 提供 IS NULL 和 IS NOT NULL 两种操作来对 NULL 特殊判断。

因此，在 WHERE 语句中我们需要做一个额外的条件判断 `referee_id IS NULL'。

**常用在 left 或 right 连接中**

## 2. length和char_length区别

对于SQL表，用于计算字符串中字符数的最佳函数是 CHAR_LENGTH(str)，它返回字符串 str 的长度。

另一个常用的函数 LENGTH(str) 在这个问题中也适用，因为列 content 只包含英文字符，没有特殊字符。否则，LENGTH() 可能会返回不同的结果，因为该函数返回字符串 str 的字节数，某些字符包含多于 1 个字节。

以字符 '¥' 为例：CHAR_LENGTH() 返回结果为 1，而 LENGTH() 返回结果为 2，因为该字符串包含 2 个字节。

| LENGTH('¥') | CHAR_LENGTH('¥') |
| ----------- | ---------------- |
| 2           | 1                |

## 3.  having 和 where区别

Having：group by 聚合时候使用的，是对聚合完成后的结果进行筛选，筛选的是 select.......from 之间的字段。

Where：进行每行的判断，及聚合时每行的数据进行判断，可以筛选的时表中的所有字段。

两者顺序：where 先于 Having
``` sql
SELECT v.customer_id, COUNT(*) AS count_no_trans
FROM Visits v
LEFT JOIN Transactions t 
ON v.visit_id = t.visit_id 
WHERE t.transaction_id is null # where放置的地方
GROUP BY v.customer_id
HAVING customer_id = 30			# having放置的地方
ORDER BY count_no_trans desc
```

## 4. 日期常用操作函数

- datediff(date1, date2)：返回两个日期之差
- date_sub(date, interval 1 day)：返回日期date减一天
- date_add(date, interval 1 day)：返回日期date加一天
- DATE_FORMAT(trans_date, '%Y-%m')：2018-12-18 转换为 2018-12

## 5. 计算常用函数

-  round(num, 3)：保留三位小数
-  avg(name='zs')：计算name=zs的平均值，得配合group by 进行使用，avg(age<25) 计算年龄小于25的平均值。avg(age<18): 计算年龄小于18所占的百分比(因为true为1，false为0)
-  ifnull(name, 0)：如果name=null，赋值默认值0
-  lead(name)：获取下一个name的值。
-  SUM(CASE WHEN age<25 THEN 1 ELSE 0 END) 等同于 SUM(IF(age<25, 1, 0))：小于二十五岁记录为1否则为0进行求和。
-  count：计算个数
   count(*) 和 count(1) 效果一样都能计算null值，count(column)不会计算null值。
   count(distinct column) 计算对应的不同的column值。

## 6. 连接

left join：保证所左边的值都出现在结果中。(有连接条件不符合右边全为null，如果左边的全都出现了就不会额外出现右边为null的行)

right join：保证所有右边的值都出现在结果中。(有链接条件，不符合左边全为null，如果右边的全都出现了就不会额外出现左边全为null的行)

inner join：严格按照连接条件。(有连接条件)

cross join：笛卡尔积，即 左边个数 * 右边个数。(不用连接条件)

搭配：is null、is not null、ifnull(column, 0)

- table1 inner/left/right join on table2 条件一，条件二，条件三。内连接自身拼接成需要的结果。

- table1 left / right join table2：

  1. left join：除了**连接条件**和**table1**的字段外table2没有的都用null进行补全。
  2. right join：除了**连接条件**和**table2**的字段外table1没有的都用null进行补全

- table1 cross table2 全连接：一般求的是不管有没有都进行最后的展示，包含一个全表
  https://leetcode.cn/problems/students-and-examinations/?envType=study-plan-v2&envId=sql-free-50

  ``` sql
  select studen_subject.student_id, studen_subject.student_name , studen_subject.subject_name, ifnull(num_subject.attended_exams, 0) attended_exams from 
  (
      select 
          s.student_id, s.student_name, sub.subject_name
      from 
          Students s
      cross join 
          Subjects sub
  ) studen_subject	# 这个表是全连接表
  left join
      (
      select student_id, subject_name, count(*) attended_exams from  # 这里比连接条件只多出一个count(*)
          Examinations 
          group by student_id, subject_name 
      ) num_subject	# 这个表是实际次数表
  on 
      studen_subject.student_id = num_subject.student_id and studen_subject.subject_name = num_subject.subject_name
  order by 
      studen_subject.student_id, studen_subject.subject_name 
  ```


## 7. 子查询



巧妙使用子查询，比如在算百分比的时候，自连接比较麻烦，可以用子查询。
``` sql
select r.contest_id, round(count(*) / (select count(*) from Users)*100, 2) percentage  from 
    Users u
    inner join Register r
    on u.user_id = r.user_id
    group by r.contest_id
    order by percentage desc, r.contest_id asc
```

https://leetcode.cn/problems/percentage-of-users-attended-a-contest/?envType=study-plan-v2&envId=sql-free-50

更新操作进行子查询的时候不能使用被更新的表内容字段。

``` sql
update table1 t1 
	set coumn1 = (
        select name from table2 t2 where t1.id = t2.id limit 1;
    )
```

上面是会进行报错的，更新时候不能进行读取操作。