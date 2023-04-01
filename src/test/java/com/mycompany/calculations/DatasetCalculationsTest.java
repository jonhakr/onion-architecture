package com.mycompany.calculations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DatasetCalculationsTest extends Assertions {

    @Nested
    class ParseDatasetId {
        @Test
        void returnsEmptyOptionalWhenParsingFails() {
            String badId = "herp";
            assertTrue(DatasetCalculations.parseDatasetId(badId).isEmpty());
        }
        @Test
        void returnsEmptyOptionalWhenIdIsNull() {
            String badId = null;
            assertTrue(DatasetCalculations.parseDatasetId(badId).isEmpty());
        }
    }

    @Nested
    class Top5CompaniesBySymbol {
        @Test
        void sortsByPriceToEarnings() {
            
        }
    }
}