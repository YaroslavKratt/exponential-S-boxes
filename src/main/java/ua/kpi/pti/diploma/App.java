package ua.kpi.pti.diploma;

import com.pengrad.telegrambot.TelegramBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ua.kpi.pti.diploma.charts.BarChartForTables;
import ua.kpi.pti.diploma.charts.CustomBarChart;
import ua.kpi.pti.diploma.tables.*;
import ua.kpi.pti.diploma.telegram_bot.StatisticSenderBot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<TableProvider> tableProvidersPool = new ArrayList<>();
        Type type = Type.USUAL;
        List<Integer> alpas = agafonovCriteriaFilter
                .filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        StatisticSenderBot bot = new StatisticSenderBot();
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


/*
       // tableProvidersPool.add(new DdtXorXor());
      //  tableProvidersPool.add(new DdtXorPlus());
        tableProvidersPool.add(new DdtPlusPlus());
       tableProvidersPool.add(new LAT());
      // tableProvidersPool.add(new ElTable());
      // tableProvidersPool.add(new LambdaTable());

        List<Integer> finalAlpas = alpas;
        tableProvidersPool.forEach(tableProvider -> {
            CustomBarChart chart = new BarChartForTables(tableProvider.getTableName());
            chart.printChart(tableProvider.calculateStatistics(finalAlpas, type)).saveChart(type.toString()).sendWitTelegram(bot);
        });*/
    }
}

