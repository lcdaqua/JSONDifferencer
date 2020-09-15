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

    public ArrayList <HashMap <String, String> > jsonEquals() throws JsonProcessingException
    {

        ArrayList <HashMap <String, String>> result = new ArrayList();
        HashMap <String, String> firstJsonMap = new Gson().fromJson(firstJson, new TypeToken<HashMap<String, Object>>() {}.getType());
        HashMap <String, String> secondJsonMap = new Gson().fromJson(secondJson, new TypeToken<HashMap<String, Object>>() {}.getType());

        HashMap <String, String> firstJsonDiff = new HashMap<>();
        HashMap <String, String> secondJsonDiff = new HashMap<>();

        //TODO Сравнение мап

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

        for (Map.Entry<String, String> firstEntry : firstJsonMap.entrySet())
        {
            if (!secondJsonMap.containsKey(firstEntry.getKey()))
            {
                firstJsonDiff.put(firstEntry.getKey(), firstJsonMap.get(firstEntry.getKey()));
            }
        }

        for (Map.Entry<String, String> secondEntry : secondJsonMap.entrySet())
        {
            if (!firstJsonMap.containsKey(secondEntry.getKey()))
            {
                secondJsonDiff.put(secondEntry.getKey(), secondJsonMap.get(secondEntry.getKey()));
            }
        }

        result.add(firstJsonDiff);
        result.add(secondJsonDiff);

        return result;
    }
}
