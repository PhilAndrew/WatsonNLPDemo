package test

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.{EntitiesOptions, RelationsOptions, SemanticRolesOptions}

/**
  * Created by philipandrew on 23/8/2017.
  */
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

  val testString = "The Parties agree that Taiho may designate certain employees reasonably acceptable to MG to visit MG’s facilities where the Plans and Budgets are being performed, for purposes of observing and participating in the performance of the Plans and Budgets.  The arrangements and duration of such visits shall be reasonably agreed by the Parties so as to minimize any disruption to MG’s business while providing Taiho meaningful participation in the performance the Plans and Budgets as requested by Taiho.  It is understood that such visits shall be on a temporary, short-term basis and shall not include longer-term residency unless agreed by the Parties.  While at MG, Taiho employees shall have full access at MG to Data and Licensed Technical Information pertaining to the Compounds and/or Products."
  val entities = new EntitiesOptions.Builder().limit(100).build()
  val concepts = new ConceptsOptions.Builder().limit(5).build
  //val relations = new RelationsOptions.Builder().build()
  val semantic = new SemanticRolesOptions.Builder().limit(500).build()

  val features = new Features.Builder().concepts(concepts).semanticRoles(semantic).entities(entities).build
  val parameters = new AnalyzeOptions.Builder().text(testString).features(features).returnAnalyzedText(true).build

  val results = understanding.analyze(parameters).execute
  System.out.println(results)
}
