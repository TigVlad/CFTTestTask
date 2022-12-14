Добрый день. Меня зовут Федосцев Владислав.

Данная инструкция предполагает ознакомление и запуск моего приложения-отклика на тестовое задание от проекта Focus Start компании Центр Финансовых технологий.

Входные данные задаются из командной строки в следующем порядке:
	- способ сортировки: по возрастанию или по убыванию. Обозначения: -a (возрастание), -d (убывание). При отсутствии данного параметра значение по умолчанию присваивается как -a;
	- тип данных: строки или целые числа. Обозначения: -s (строки - String), i (целые числа - int);
	- имя выходного файла в виде строки, куда будет помещена информация после сортировки;
	- имя или несколько имён файлов, откуда будет читаться информация для сортировки.

В данном приложении есть пять классов:
    - класс Main, в котором происходят основные действия для работы с данными, расположен в корне приложения;
    - классы ReaderFromFile и WriterToFile для чтения из файла и записи в файл соответственно, расположены в пакете reader и writer соответственно;
    - классы IntegerMergeSorter и StringMergeSorter для сортировки слиянием разных входных данных, в данном случае строк типа String и целых чисел типа int, классы расположены внутри пакета sorter.
Внутри каждого класса есть методы с приложенной к ним документацией.


При открытии данного проекта из архива необходимо распраковать архив в удобную директорию.
При открытии данного проекта из BitBucket требуется склонировать в удобную директорию проект: git clone https://github.com/TigVlad/CFTTestTask.git.
После сохранения проекта требуется открыть любую предпочитаемую среду разработки, из меню File выбрать пункт Open и выбрать распакованный/склонированный проект.
После открытия проекта необходимо в параметрах командной строки указать требуемые параметры и непосредственно запустить проект из среды разработки нажатием на кнопку Run.

При возникновении ошибок, связанных с повреждённой информацией в файлах, ошибки обрабатываются. Все предупреждения логируются и при ошибках выводятся в консоль с информацией о возникшей проблеме.

Из минусов: не хватило времени на реализацию устойчивости программы к файлам, которые превосходят по вместимости оперативную память.

При возникновении ошибок в программе, повреждённых файлов или недопустимом формате данных программа не падает, продолжает свою работу. Все данные записываются в выходной файл, кроме повреждённых данных, в остальном - все чистые данные записываются.

Сортировка по возрастанию и убыванию работает исправно.
