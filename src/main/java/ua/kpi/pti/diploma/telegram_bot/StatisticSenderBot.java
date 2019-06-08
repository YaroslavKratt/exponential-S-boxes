package ua.kpi.pti.diploma.telegram_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.kpi.pti.diploma.AgafonovCriteriaFilter;
import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.charts.BarChartForTables;
import ua.kpi.pti.diploma.charts.CustomBarChart;
import ua.kpi.pti.diploma.tables.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this is totaly shit? but works and
public class StatisticSenderBot extends TelegramLongPollingBot {
    private long chatId;
    Map<Long,Integer> map = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        SendMessage message;
        if(map.get(chatId)==0) {
            message = new SendMessage().setChatId(chatId).setText("Ну привет, чё хотел?");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(map.get(chatId)==1) {
            message = new SendMessage().setChatId(chatId).setText("И чё?");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(map.get(chatId)==2) {
            message = new SendMessage().setChatId(chatId).setText("Начинаешь надоедать");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if(map.get(chatId)==3) {
            message = new SendMessage().setChatId(chatId).setText("Уходи, дверь закрой");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(map.get(chatId)==4) {
            message = new SendMessage().setChatId(chatId).setText("Ну достал ужеб э!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if(map.get(chatId)==5) {
            message = new SendMessage().setChatId(chatId).setText("у блэт, настойчивый, ну-ну");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if(map.get(chatId)==5||map.get(chatId)==6||map.get(chatId)==7||map.get(chatId)==8||map.get(chatId)==9||map.get(chatId)==10||map.get(chatId)==11||map.get(chatId)==12) {
            message = new SendMessage().setChatId(chatId).setText("Я могу так хоть весь день, а ты? Чё написал диплом? А? Больно да?  получай!!!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (map.get(chatId)==13) {
            message = new SendMessage().setChatId(chatId).setText("Ты победил, но но стоило оно того? Ты потратил время, которое больше не вернуть. ты мог сделать дела, которые уже несделаешь, но ты потратил своё драгоценное время на бездушную машиную! АЗАЗАЗ ЛАЛКА!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        map.putIfAbsent(chatId,1);
        map.put(chatId,map.get(chatId)+1);
       // SendMessage message = new SendMessage().setChatId(chatId).setText("Lets go? /go");
    /*    try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (update.getMessage().getText().equals("/go")) {


            AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
            List<TableProvider> tableProvidersPool = new ArrayList<>();
            Type type = Type.AFFINE_ON_EXIT;
            message = new SendMessage().setChatId(chatId).setText("WORKING!!!!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            List<Integer> alpas = agafonovCriteriaFilter
                    .filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
            alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);
            tableProvidersPool.add(new DdtXorXor());
            tableProvidersPool.add(new DdtXorPlus());
            tableProvidersPool.add(new DdtPlusPlus());
            tableProvidersPool.add(new LAT());
           //  tableProvidersPool.add(new ElTable());
        //    tableProvidersPool.add(new LambdaTable());

            List<Integer> finalAlpas = alpas;
            tableProvidersPool.forEach(tableProvider -> {
                CustomBarChart chart = new BarChartForTables(tableProvider.getTableName());
                chart.printChart(tableProvider.calculateStatistics(finalAlpas, type)).saveChart(type.toString());

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId)
                        .setPhoto(new File (((BarChartForTables) chart).getPath()));*/

               /* try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            });*/


    }

    @Override
    public String getBotUsername() {

        return "KrattorBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "867857650:AAFtNeCsx8cwjv00lgAUK86F4Q0tHEIy3UE";
    }


}

