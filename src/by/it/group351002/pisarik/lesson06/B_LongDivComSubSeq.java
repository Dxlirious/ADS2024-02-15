package by.it.group351002.pisarik.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        // Получение общей длины последовательности
        int n = scanner.nextInt();
        int[] m = new int[n]; // Массив для хранения последовательности
        int[] d = new int[n]; // Массив для хранения длины максимальной возрастающей подпоследовательности, заканчивающейся на каждом индексе

        // Чтение всей последовательности
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
            d[i] = 1; // Инициализация длины максимальной возрастающей подпоследовательности, заканчивающейся на каждом индексе, как 1
        }

        // Реализация алгоритма на основе динамического программирования
        int result = 0; // Переменная для хранения длины максимальной возрастающей подпоследовательности
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Если текущий элемент делится на предыдущий без остатка и длина максимальной возрастающей подпоследовательности, заканчивающейся на предыдущем индексе, плюс 1 больше длины максимальной возрастающей подпоследовательности, заканчивающейся на текущем индексе
                if ((m[i] % m[j] == 0) && (d[j] + 1 > d[i])) {
                    d[i] = d[j] + 1; // Обновление длины максимальной возрастающей подпоследовательности, заканчивающейся на текущем индексе
                    if (d[i] > result) {
                        result = d[i]; // Обновление длины максимальной возрастающей подпоследовательности
                    }
                }
            }
        }

        // !!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

}