import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

//ambil data
String pathData = 'excel'

//declare data
String penghasilan = findTestData(pathData).getValue('Penghasilan', 2)
String pengeluaran = findTestData(pathData).getValue('Pengeluaran', 2)
String jangkawaktu = findTestData(pathData).getValue('Jangka Waktu', 2)

//testobject Alert
TestObject AlertNegtest = new TestObject()
AlertNegtest.addProperty("xpath", ConditionType.EQUALS, "//p[@style='position: relative; bottom: 30px; height: 0px; font-family: Helvetica; font-weight: 400; color: red; margin-bottom: 0px;']")

//startAutomation
WebUI.openBrowser('')
	
	WebUI.navigateToUrl('https://www.btnproperti.co.id/tools/hitung-harga-properti')
	
		WebUI.waitForPageLoad(5)
	
			WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pemasukan_obj'),
		penghasilan)
	
			WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pengeluaran_obj'),
		pengeluaran)
	
			WebUI.selectOptionByValue(findTestObject('Object Repository/HitungHargaPropertiBTN/JangkaWaktu'),
		jangkawaktu, true)
	
	WebUI.verifyElementVisible(AlertNegtest)
	
WebUI.closeBrowser()