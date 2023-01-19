package pl.bobak.integrations.fdademo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import pl.bobak.integrations.fdademo.model.DrugRecordApplicationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenfdaSearchServiceTest {

    @Autowired
    private OpenfdaSearchService searchServiceUnderTest;

    @Test
    void shouldCreateUrlWithoutLimitValueAndWithoutBrandName() {
        // given
        String expectedUrl = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"test input\"";
        String phrase = "test input";

        // when
        String computedUrl = searchServiceUnderTest.createUrl(phrase, null, null, null);

        // then
        assertEquals(expectedUrl, computedUrl);
    }

    @Test
    void shouldCreateUrlWithoutLimitValueAndWithBrandName() {
        // given
        String expectedUrl = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:" +
                "\"test input\"+AND+openfda.brand_name:\"another test input\"";
        String firstPhrase = "test input";
        String secondPhrase = "another test input";

        // when
        String computedUrl = searchServiceUnderTest.createUrl(firstPhrase, secondPhrase, null, null);

        // then
        assertEquals(expectedUrl, computedUrl);
    }

    @Test
    void shouldCreateUrlWithLimitValueAndWithBrandName() {
        // given
        String expectedUrl = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:" +
                "\"test input\"+AND+openfda.brand_name:\"another test input\"&limit=3";
        String phrase = "test input";
        String secondPhrase = "another test input";
        Integer limit = 3;

        // when
        String computedUrl = searchServiceUnderTest.createUrl(phrase, secondPhrase, limit, null);

        // then
        assertEquals(expectedUrl, computedUrl);
    }

    @Test
    void shouldCreateUrlWithLimitValueWithSkipValueAndWithBrandName() {
        // given
        String expectedUrl = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:" +
                "\"test input\"+AND+openfda.brand_name:\"another test input\"&limit=3&skip=9";
        String phrase = "test input";
        String secondPhrase = "another test input";
        Integer limit = 3;
        Integer page = 4;

        // when
        String computedUrl = searchServiceUnderTest.createUrl(phrase, secondPhrase, limit, page);

        // then
        assertEquals(expectedUrl, computedUrl);
    }

    @Test
    void shouldExtractThreeDtosFromValidApiResponse() throws JSONException, JsonProcessingException {
        // given
        String validApiResponse = """
                {
                  "meta": {
                    "disclaimer": "Do not rely on openFDA to make decisions regarding medical care. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated. We may limit or otherwise restrict your access to the API in line with our Terms of Service.",
                    "terms": "https://open.fda.gov/terms/",
                    "license": "https://open.fda.gov/license/",
                    "last_updated": "2023-01-17",
                    "results": {
                      "skip": 0,
                      "limit": 3,
                      "total": 1644
                    }
                  },
                  "results": [
                    {
                      "submissions": [
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "10",
                          "submission_status": "AP",
                          "submission_status_date": "20221027",
                          "review_priority": "STANDARD",
                          "submission_class_code": "LABELING",
                          "submission_class_code_description": "Labeling"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "9",
                          "submission_status": "AP",
                          "submission_status_date": "20201002",
                          "submission_class_code": "REMS",
                          "submission_class_code_description": "REMS"
                        },
                        {
                          "submission_type": "ORIG",
                          "submission_number": "1",
                          "submission_status": "AP",
                          "submission_status_date": "20180423",
                          "review_priority": "STANDARD",
                          "submission_class_code": "UNKNOWN"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "5",
                          "submission_status": "AP",
                          "submission_status_date": "20190912",
                          "submission_class_code": "REMS",
                          "submission_class_code_description": "REMS"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "1",
                          "submission_status": "AP",
                          "submission_status_date": "20200803",
                          "review_priority": "STANDARD",
                          "submission_class_code": "LABELING",
                          "submission_class_code_description": "Labeling"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "11",
                          "submission_status": "AP",
                          "submission_status_date": "20221012",
                          "submission_class_code": "REMS",
                          "submission_class_code_description": "REMS"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "8",
                          "submission_status": "AP",
                          "submission_status_date": "20200417",
                          "submission_class_code": "REMS",
                          "submission_class_code_description": "REMS"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "6",
                          "submission_status": "AP",
                          "submission_status_date": "20200803",
                          "review_priority": "STANDARD",
                          "submission_class_code": "LABELING",
                          "submission_class_code_description": "Labeling"
                        },
                        {
                          "submission_type": "SUPPL",
                          "submission_number": "2",
                          "submission_status": "AP",
                          "submission_status_date": "20190530",
                          "submission_class_code": "REMS",
                          "submission_class_code_description": "REMS"
                        }
                      ],
                      "application_number": "ANDA209824",
                      "sponsor_name": "TEVA PHARMS USA",
                      "openfda": {
                        "application_number": [
                          "ANDA209824"
                        ],
                        "brand_name": [
                          "VIGABATRIN"
                        ],
                        "generic_name": [
                          "VIGABATRIN"
                        ],
                        "manufacturer_name": [
                          "Actavis Pharma, Inc."
                        ],
                        "product_ndc": [
                          "0591-3955"
                        ],
                        "product_type": [
                          "HUMAN PRESCRIPTION DRUG"
                        ],
                        "route": [
                          "ORAL"
                        ],
                        "substance_name": [
                          "VIGABATRIN"
                        ],
                        "rxcui": [
                          "250820"
                        ],
                        "spl_id": [
                          "bb26e312-15c4-4764-ae8b-b309c3d8d362"
                        ],
                        "spl_set_id": [
                          "1ce1c52d-441a-4ed7-afff-568192197454"
                        ],
                        "package_ndc": [
                          "0591-3955-11",
                          "0591-3955-50"
                        ],
                        "nui": [
                          "N0000175753"
                        ],
                        "pharm_class_epc": [
                          "Anti-epileptic Agent [EPC]"
                        ],
                        "unii": [
                          "GR120KRT6K"
                        ]
                      },
                      "products": [
                        {
                          "product_number": "001",
                          "reference_drug": "No",
                          "brand_name": "VIGABATRIN",
                          "active_ingredients": [
                            {
                              "name": "VIGABATRIN",
                              "strength": "500MG/PACKET"
                            }
                          ],
                          "reference_standard": "No",
                          "dosage_form": "FOR SOLUTION",
                          "route": "ORAL",
                          "marketing_status": "Prescription",
                          "te_code": "AA"
                        }
                      ]
                    },
                    {
                      "submissions": [
                        {
                          "submission_type": "ORIG",
                          "submission_number": "1",
                          "submission_status": "AP",
                          "submission_status_date": "20171004",
                          "review_priority": "STANDARD",
                          "submission_class_code": "UNKNOWN"
                        }
                      ],
                      "application_number": "ANDA206242",
                      "sponsor_name": "EUGIA PHARMA",
                      "openfda": {
                        "application_number": [
                          "ANDA206242"
                        ],
                        "brand_name": [
                          "MOXIFLOXACIN"
                        ],
                        "generic_name": [
                          "MOXIFLOXACIN"
                        ],
                        "manufacturer_name": [
                          "Aurobindo Pharma Limited",
                          "NorthStar Rx LLC"
                        ],
                        "product_ndc": [
                          "65862-840",
                          "16714-643"
                        ],
                        "product_type": [
                          "HUMAN PRESCRIPTION DRUG"
                        ],
                        "route": [
                          "OPHTHALMIC"
                        ],
                        "substance_name": [
                          "MOXIFLOXACIN HYDROCHLORIDE"
                        ],
                        "rxcui": [
                          "403818"
                        ],
                        "spl_id": [
                          "bdb0fe50-978d-4a1b-a3be-9567d7375ad8",
                          "c11c970c-ffa6-4661-b515-708bae4eddd6"
                        ],
                        "spl_set_id": [
                          "8877013d-9b49-40f7-a2c4-99bb519e104e",
                          "cdc0b0eb-5f56-45c7-b9fd-364752a95ff1"
                        ],
                        "package_ndc": [
                          "65862-840-03",
                          "16714-643-01"
                        ],
                        "unii": [
                          "C53598599T"
                        ]
                      },
                      "products": [
                        {
                          "product_number": "001",
                          "reference_drug": "No",
                          "brand_name": "MOXIFLOXACIN HYDROCHLORIDE",
                          "active_ingredients": [
                            {
                              "name": "MOXIFLOXACIN HYDROCHLORIDE",
                              "strength": "EQ 0.5% BASE"
                            }
                          ],
                          "reference_standard": "No",
                          "dosage_form": "SOLUTION/DROPS",
                          "route": "OPHTHALMIC",
                          "marketing_status": "Prescription",
                          "te_code": "AT1"
                        }
                      ]
                    },
                    {
                      "submissions": [
                        {
                          "submission_type": "ORIG",
                          "submission_number": "1",
                          "submission_status": "AP",
                          "submission_status_date": "20210917",
                          "review_priority": "STANDARD",
                          "submission_class_code": "UNKNOWN",
                          "application_docs": [
                            {
                              "id": "68922",
                              "url": "https://www.accessdata.fda.gov/drugsatfda_docs/appletter/2021/212589Orig1s000ltr.pdf",
                              "date": "20211008",
                              "type": "Letter"
                            }
                          ]
                        }
                      ],
                      "application_number": "ANDA212589",
                      "sponsor_name": "EYWA",
                      "openfda": {
                        "application_number": [
                          "ANDA212589"
                        ],
                        "brand_name": [
                          "QUINIDINE GLUCONATE"
                        ],
                        "generic_name": [
                          "QUINIDINE GLUONATE"
                        ],
                        "manufacturer_name": [
                          "Eywa Pharma Inc"
                        ],
                        "product_ndc": [
                          "71930-016"
                        ],
                        "product_type": [
                          "HUMAN PRESCRIPTION DRUG"
                        ],
                        "route": [
                          "ORAL"
                        ],
                        "substance_name": [
                          "QUINIDINE GLUCONATE"
                        ],
                        "rxcui": [
                          "852920"
                        ],
                        "spl_id": [
                          "d4e8ed08-f426-4fdb-9dd3-d46730e9f81b"
                        ],
                        "spl_set_id": [
                          "4db375a3-0cff-4fa7-af7a-2a49e0b922d0"
                        ],
                        "package_ndc": [
                          "71930-016-12"
                        ],
                        "unii": [
                          "R6875N380F"
                        ]
                      },
                      "products": [
                        {
                          "product_number": "001",
                          "reference_drug": "No",
                          "brand_name": "QUINIDINE GLUCONATE",
                          "active_ingredients": [
                            {
                              "name": "QUINIDINE GLUCONATE",
                              "strength": "324MG"
                            }
                          ],
                          "reference_standard": "No",
                          "dosage_form": "TABLET, EXTENDED RELEASE",
                          "route": "ORAL",
                          "marketing_status": "Prescription",
                          "te_code": "AB"
                        }
                      ]
                    }
                  ]
                }
                """.indent(1);

        // when
        List<DrugRecordApplicationDto> results = searchServiceUnderTest.getApplicationDataFromResult(validApiResponse);

        // then
        assertEquals(3, results.size());

    }

}
