import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class jsonDifference
{
    String firstJson;
    String secondJson;

    public jsonDifference(String firstJson, String secondJson)
    {
        this.firstJson = firstJson;
        this.secondJson = secondJson;
    }

    //Метод который проверяет различия между двумя JSON строками. Вывод в виде листа из мап
    public ArrayList <HashMap <String, String> > jsonEquals() throws JsonProcessingException
    {

        ArrayList <HashMap <String, String>> result = new ArrayList();

        //Парсим строки на мапы
        HashMap <String, String> firstJsonMap = new Gson().fromJson(firstJson, new TypeToken<HashMap<String, Object>>() {}.getType());
        HashMap <String, String> secondJsonMap = new Gson().fromJson(secondJson, new TypeToken<HashMap<String, Object>>() {}.getType());

        //Мапы для записи различий
        HashMap <String, String> firstJsonDiff = new HashMap<>();
        HashMap <String, String> secondJsonDiff = new HashMap<>();

        //Проверка на различия между ключ1-ключ2, значение1-значение2, при различии происходит запись в мапы пары ключ-значение
        for (Map.Entry<String, String> firstEntry : firstJsonMap.entrySet())
        {
            for (Map.Entry<String, String> secondEntry : secondJsonMap.entrySet())
            {
                if ((firstEntry.getKey().equals(secondEntry.getKey()) & !firstJsonMap.get(firstEntry.getKey()).equals(secondJsonMap.get(secondEntry.getKey())))
                | (!firstEntry.getKey().equals(secondEntry.getKey()) & firstJsonMap.get(firstEntry.getKey()).equals(secondJsonMap.get(secondEntry.getKey()))))
                {
                    firstJsonDiff.put(firstEntry.getKey(), firstJsonMap.get(firstEntry.getKey()));
                    secondJsonDiff.put(secondEntry.getKey(), secondJsonMap.get(secondEntry.getKey()));
                }
            }
        }

        //Проверка на содержание ключа1 в мапе2
        for (Map.Entry<String, String> firstEntry : firstJsonMap.entrySet())
        {
            if (!secondJsonMap.containsKey(firstEntry.getKey()))
            {
                firstJsonDiff.put(firstEntry.getKey(), firstJsonMap.get(firstEntry.getKey()));
            }
        }

        //Проверка на содержание ключа2 в мапе1
        for (Map.Entry<String, String> secondEntry : secondJsonMap.entrySet())
        {
            if (!firstJsonMap.containsKey(secondEntry.getKey()))
            {
                secondJsonDiff.put(secondEntry.getKey(), secondJsonMap.get(secondEntry.getKey()));
            }
        }

        //Запись результат в лист
        result.add(firstJsonDiff);
        result.add(secondJsonDiff);

        return result;
    }
}
