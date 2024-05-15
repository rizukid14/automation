import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.lang.reflect.Array

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

//ambil data 
String pathData = 'excel'

//declare data
String penghasilan = findTestData(pathData).getValue('Penghasilan', 2)
String pengeluaran = findTestData(pathData).getValue('Pengeluaran', 2)
String jangkawaktu = findTestData(pathData).getValue('Jangka Waktu', 2)
String ssPath = "Screenshots"

//---------------start automation--------------------------//
WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.btnproperti.co.id/tools/hitung-harga-properti')

WebUI.waitForPageLoad(5)

WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pemasukan_obj'), 
    penghasilan)

WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pengeluaran_obj'), 
    pengeluaran)

WebUI.selectOptionByValue(findTestObject('Object Repository/HitungHargaPropertiBTN/JangkaWaktu'), 
    jangkawaktu, true)

try {
    WebUI.verifyElementNotClickable(findTestObject('Object Repository/HitungHargaPropertiBTN/button_Hitung'))
    println("Element is not clickable.")
	WebUI.comment("Tombol gabisa diklik, mantap")
} catch (Exception e) {
    println("Element is clickable.")
	WebUI.comment("Lah? Kok bisa diklik")
}

/*TestObject Alert = new TestObject()
Alert.addXpath(xpath)*/

WebUI.takeScreenshot()

WebUI.closeBrowser()