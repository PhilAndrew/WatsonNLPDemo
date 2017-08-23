package test

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.{RelationsOptions, SemanticRolesOptions}

object TestApp extends App {

  import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding
  import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults
  import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions
  import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions
  import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features
  import collection.JavaConverters._

  val serviceUsername = ""
  val servicePassword = ""
  val url = "https://gateway.watsonplatform.net/natural-language-understanding/api"
  val getDefaultHeaders = Map.empty[String, String]

  val understanding = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27)
  understanding.setUsernameAndPassword(serviceUsername, servicePassword)
  understanding.setEndPoint(url)
  understanding.setDefaultHeaders(getDefaultHeaders.asJava)

  val testString = "The Parties agree that Taiho may designate certain employees reasonably acceptable to MG."
  val concepts = new ConceptsOptions.Builder().limit(5).build
  val semantic = new SemanticRolesOptions.Builder().limit(500).build()

  val features = new Features.Builder().concepts(concepts).semanticRoles(semantic).build
  val parameters = new AnalyzeOptions.Builder().text(testString).features(features).returnAnalyzedText(true).build

  val results = understanding.analyze(parameters).execute
  System.out.println(results)
}
