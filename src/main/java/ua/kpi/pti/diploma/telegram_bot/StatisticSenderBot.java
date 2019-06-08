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
import java.util.List;
//this is totaly shit? but works and
public class StatisticSenderBot extends TelegramLongPollingBot {
    private long chatId;

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Got message");
        chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage().setChatId(chatId).setText("Lets go? /go");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (update.getMessage().getText().equals("/go")) {


            AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
            List<TableProvider> tableProvidersPool = new ArrayList<>();
            Type type = Type.AFFINE_ON_ENTER;
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
                        .setPhoto(new File (((BarChartForTables) chart).getPath()));

                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            });

        }
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

