package com.mycompany.calculations;

import com.mycompany.data.Company;
import com.mycompany.data.Dataset;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

public class DatasetCalculations {
    public static Optional<Integer> parseDatasetId(String id) {
        try {
            return Optional.of(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    public static List<String> top5CompaniesBySymbol(Dataset dataset) {
        return dataset.getCompanies().stream()
                .sorted(comparingDouble(DatasetCalculations::priceToEarnings))
                .limit(5)
                .map(Company::getSymbol)
                .collect(Collectors.toList());
    }

    private static double priceToEarnings(Company company1) {
        return company1.getPrice() / company1.getEarningsPerShare();
    }
}
