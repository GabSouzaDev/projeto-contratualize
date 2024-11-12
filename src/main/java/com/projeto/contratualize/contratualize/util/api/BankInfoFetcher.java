/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.contratualize.contratualize.util.api;


import com.projeto.contratualize.contratualize.model.Bank;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * API que disponibiliza todos os bancos por códigos.
 * @author gabri
 */
public class BankInfoFetcher {
    // URL base do BrasilAPI para informações bancárias
    private static final String API_URL = "https://brasilapi.com.br/api/banks/v1";
    

    private List<Bank> banks;

    public BankInfoFetcher() {
        fetchBanks(); //Carrega a lista de bancos ao instanciar
    }

    private void fetchBanks() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                Gson gson = new Gson();
                Type bankListType = new TypeToken<List<Bank>>() {}.getType();
                banks = gson.fromJson(jsonResponse, bankListType);
                System.out.println("Bancos carregados: " + banks.size());
                /*for(Bank bank : banks) {
                    System.out.println("Código: " + bank.getCode() + ", Nome: " + bank.getName());
                }*/
            } else {
                System.out.println("Erro: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBankNameByCode(int bankCode) {
        if (banks == null) {
            fetchBanks(); //Tenta buscar os bancos se não foram carregados
        }
        
        // Procura o banco pelo código
        Optional<Bank> bank = banks.stream()
                                    .filter(b -> {
                                        String bankCodeStr = b.getCode();
                                        return bankCodeStr != null && Integer.parseInt(bankCodeStr) == bankCode;
                                            }) // Conversão para int
                                    .findFirst();
        return bank.map(Bank::getName).orElse(null); // Retorna o nome ou null se não encontrado
    }

    public static void main(String[] args) {
        BankInfoFetcher fetcher = new BankInfoFetcher();
        
        //Teste de requisição da API
        System.out.println(fetcher.getBankNameByCode(1));
        System.out.println(fetcher.getBankNameByCode(70));
        System.out.println(fetcher.getBankNameByCode(539));
        fetcher.fetchBanks();
    }
}