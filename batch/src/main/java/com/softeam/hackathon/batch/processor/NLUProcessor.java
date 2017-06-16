package com.softeam.hackathon.batch.processor;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.RelationsOptions;
import com.softeam.hackathon.batch.data.Transaction;
import com.softeam.hackathon.batch.utils.DateUtils;

public class NLUProcessor implements ItemProcessor<String, Transaction> {

    private String id = "646f977d-e413-42cc-9128-d7b26db19733";
    private String pwd = "tbmvB8thvueI";
    private String model = "10:b0651a3f-8a4d-44b9-94ae-706783849598";

    private NaturalLanguageUnderstanding nlu = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, id, pwd);

    @Override
    public Transaction process(String text) throws Exception {
        EntitiesOptions entOpt = new EntitiesOptions.Builder().model(model).build();
        RelationsOptions relOpt = new RelationsOptions.Builder().model(model).build();

        Features feat = new Features.Builder().entities(entOpt).relations(relOpt).build();
        AnalyzeOptions opt = new AnalyzeOptions.Builder().features(feat).language("en").text(text).build();
        AnalysisResults results = nlu.analyze(opt).execute();
        List<EntitiesResult> entitiesResultList = results.getEntities();

        return convert(entitiesResultList);
    }

    public Transaction convert(List<EntitiesResult> entitiesResultList) {
        Transaction transaction = new Transaction();

        String productName = null;
        String productIsin = null;
        String productType = "SHARE";

        transaction.setCurrency("EUR");
        transaction.setBuyer("La Banque Postale");
        transaction.setSeller("Haribo & Co");


        transaction.setUnitPrice(Double.valueOf("1"));
        transaction.setQuantity(Integer.valueOf("1"));
        transaction.setFees(Integer.valueOf("0"));
        transaction.setTotalAmount(Double.valueOf("0"));
        transaction.setTradeDate(new Date());

        for (EntitiesResult entitiesResult : entitiesResultList) {
            switch (entitiesResult.getType()) {
                case "Buyer":
                    transaction.setBuyer(entitiesResult.getText());
                    break;
                case "Seller":
                    transaction.setSeller(entitiesResult.getText());
                    break;
                case "Product_name":
                    productName = entitiesResult.getText();
                    break;
                case "Product_ISIN":
                    productIsin = entitiesResult.getText();
                    break;
                case "Product_type":
                    if (StringUtils.isNotBlank(entitiesResult.getText())) {
                        productType = entitiesResult.getText();
                    }
                    break;
                case "Unit_price":
                    transaction.setUnitPrice(Double.valueOf(entitiesResult.getText()));
                    break;
                case "Quantity":
                    transaction.setQuantity(Integer.valueOf(entitiesResult.getText()));
                    break;
                case "Fees":
                    transaction.setFees(Integer.valueOf(entitiesResult.getText()));
                    break;
                case "Total_amount":
                    transaction.setTotalAmount(Double.valueOf(entitiesResult.getText()));
                    break;
                case "Trade_date":
                    LocalDate localDate = DateUtils.transformStringMailToDate(entitiesResult.getText());
                    transaction.setTradeDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    break;
                case "Currency":
                    transaction.setCurrency(entitiesResult.getText());
                    break;
                default:
                    throw new InvalidParameterException("bad value from nlu");
            }
        }

        List<String> dataList = new ArrayList<>();
        if (StringUtils.isNotBlank(productType)) {
            dataList.add(productType);
        }
        if (StringUtils.isNotBlank(productName)) {
            dataList.add(productName);
        }
        if (StringUtils.isNotBlank(productIsin)) {
            dataList.add(productIsin);
        }

        if (transaction.getTotalAmount().equals(new Double("0"))) {
            transaction.setTotalAmount((transaction.getUnitPrice() * transaction.getQuantity()) + transaction.getFees());
        }

        transaction.setProduct(StringUtils.join(dataList, ';'));

        System.out.println(transaction);

        return transaction;
    }
}
