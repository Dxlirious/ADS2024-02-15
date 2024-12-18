package by.it.group351002.pisarik.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        // Загружаем входные данные из файла "dataC.txt"
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        // Вызываем метод getNotUpSeqSize, чтобы получить результат
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготавливаемся к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // Получаем длину последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        int[] d = new int[n];
        // Читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
            d[i] = 1;
        }
        // Реализуем логику задачи, используя методы динамического программирования (!!!)
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Если текущий элемент больше или равен предыдущему элементу, и длина последовательности, заканчивающейся на предыдущем элементе, плюс один больше, чем длина последовательности, заканчивающейся на текущем элементе
                if ((m[j]>=m[i])&&(d[j]+1>d[i])){
                    d[i] = d[j] + 1;
                    if (d[i]>result){
                        result = i;
                    }
                }
            }
        }

        // Восстанавливаем самую длинную невозрастающую подпоследовательность
        int[] res = new int[d[result]];
        res[d[result]-1] = m[result];
        int tempres = d[result]-2;

        for (int i = result-1; i >= 0; i--) {
            if ((d[i]==tempres+1) && (m[i]>=res[tempres+1])) {
                res[tempres] = m[i];
                tempres--;
            }
        }

        System.out.println(d[result]);
        for (int i = 0; i < d[result]; i++) {
            System.out.print(res[i] + " ");
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return d[result];
    }

}