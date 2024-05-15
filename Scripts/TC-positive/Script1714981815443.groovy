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
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.nio.file.Files
import java.nio.file.Paths

//ambil data 
String pathData = 'excel'

//declare data
String penghasilan = findTestData(pathData).getValue('Penghasilan', 1) //row 1
String pengeluaran = findTestData(pathData).getValue('Pengeluaran', 1)
String jangkawaktu = findTestData(pathData).getValue('Jangka Waktu', 1)

//---------------------menghitung di katalon-------------------------------//
//convert strings ke Integer
def a = penghasilan.toInteger()
def b = pengeluaran.toInteger()
def c = jangkawaktu.toInteger()

// Define hasil HITUNG
def hitung = ((a - b) * (c*12)) / 3
println(hitung) // Output the result
def hasil = hitung.toString()

// Convert hasil HITUNG
String hasilconv = 'Rp ' + hasil.replaceAll(/(\d)(?=(\d{3})+$)/, '$1.')
println(hasilconv)
WebUI.comment(hasilconv)

//---------------------start automation----------------------//
WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.btnproperti.co.id/tools/hitung-harga-properti')

WebUI.waitForPageLoad(5)

WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pemasukan_obj'), 
    penghasilan)

WebUI.setText(findTestObject('Object Repository/HitungHargaPropertiBTN/Pengeluaran_obj'), 
    pengeluaran)

WebUI.selectOptionByValue(findTestObject('Object Repository/HitungHargaPropertiBTN/JangkaWaktu'), 
    jangkawaktu, true)

WebUI.click(findTestObject('Object Repository/HitungHargaPropertiBTN/button_Hitung'))

// define hasil WEB
def hasilweb = WebUI.verifyElementText(findTestObject('Object Repository/HitungHargaPropertiBTN/Hasil'), 
    hasilconv)

/*---------cek hasil WEB dengan langsung mengambil variable hasil convert
, jika hasil web = hasil conv akan menghasilkan TRUE----------------*/

println(hasilweb)

// mengecek apakah hasil WEB TRUE atau FALSE
if	(hasilweb == true) {
	println('Sesuai')	
}
else {
	println('Gagal')}

WebUI.takeScreenshot()

WebUI.delay(3)

WebUI.closeBrowser()



	

