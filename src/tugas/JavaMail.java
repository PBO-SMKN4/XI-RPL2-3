package tugas;

import java.util.HashMap;
import java.util.Map;

public class JavaMail {
    // SMTP info
        private String host = "smtp.gmail.com";
        private String port = "587";
        private String myAcount = "schoolmanagementassets@gmail.com";
        private String password = "Sma12345_";
        
        private String idPinjam;
        private String namaUser;
        private String namaBarang;
        private String jml;
        private String tglPinjam;
        private String tglExp;
        
        private String receiver ;
        String subject = "School Management Assets";


    public void send(){
        StringBuffer body = 
                new StringBuffer("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"<head>\n" +
"<meta name=\"viewport\" content=\"width=device-width\" />\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
"<title>School Management Assets</title>\n" +
"\n" +
"\n" +
"<style type=\"text/css\">\n" +
"img {\n" +
"max-width: 100%;\n" +
"}\n" +
"body {\n" +
"-webkit-font-smoothing: antialiased; -webkit-text-size-adjust: none; width: 100% !important; height: 100%; line-height: 1.6em;\n" +
"}\n" +
"body {\n" +
"background-color: #f6f6f6;\n" +
"}\n" +
"@media only screen and (max-width: 640px) {\n" +
"  body {\n" +
"    padding: 0 !important;\n" +
"  }\n" +
"  h1 {\n" +
"    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
"  }\n" +
"  h2 {\n" +
"    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
"  }\n" +
"  h3 {\n" +
"    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
"  }\n" +
"  h4 {\n" +
"    font-weight: 800 !important; margin: 20px 0 5px !important;\n" +
"  }\n" +
"  h1 {\n" +
"    font-size: 22px !important;\n" +
"  }\n" +
"  h2 {\n" +
"    font-size: 18px !important;\n" +
"  }\n" +
"  h3 {\n" +
"    font-size: 16px !important;\n" +
"  }\n" +
"  .container {\n" +
"    padding: 0 !important; width: 100% !important;\n" +
"  }\n" +
"  .content {\n" +
"    padding: 0 !important;\n" +
"  }\n" +
"  .content-wrap {\n" +
"    padding: 10px !important;\n" +
"  }\n" +
"  .invoice {\n" +
"    width: 100% !important;\n" +
"  }\n" +
"}\n" +
"</style>\n" +
"</head>");
        
        
        body.append("<body itemscope itemtype=\"http://schema.org/EmailMessage\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: none; width: 100% !important; height: 100%; line-height: 1.6em; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">\n" +
"\n" +
"<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\">\n" +
"  <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"    <td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" +
"		<td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important; margin: 0 auto;\" valign=\"top\">\n" +
"			<div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\">\n" +
"        <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\" bgcolor=\"#fff\">\n" +
"          <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"            <td class=\"alert alert-warning\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 16px; vertical-align: top; color: #fff; font-weight: 500; text-align: center; border-radius: 3px 3px 0 0; background-color: #17ca9a; margin: 0; padding: 20px;\" align=\"center\" bgcolor=\"#FF9F00\" valign=\"top\">\n" +
"						  <font style=\"font-size: 20px;\"><b>School Management Assets</b></font>\n" +
"						</td>\n" +
"          </tr>\n" +
"          \n" +
"          <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"            <td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\">\n" +
"              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"                  <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\">\n" +
"                    <h1>Hai " + getNamaUser() + "!</h1>\n" +
"                    <h1>Peminjaman barang berhasil</h1>\n" +
"                    <br>\n" +
"                    Segera ambil barang sebelum <strong style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">");
        body.append(getTglExp());
        body.append("</strong><br>\n" +
"									</td>\n" +
"                </tr>\n" +
"                \n" +
"                <tr>\n" +
"                  <td>\n" +
"                    <table border=\"1px\"style=\"border-radius:10px; padding:0px;\" padding=\"0px\" width=\"100%\">\n" +
"                      <tr>\n" +
"                        <td style=\"border: 0px; padding: 20px;\">\n" +
"                          <font>Tanggal Peminjaman</font>\n" +
"                          <h3 style=\"margin-top: 4px;\">");
        //TANGGAL PINJAM
        body.append(getTglPinjam());
        body.append("</h3>\n" +
"                          \n" +
"                          <font>ID Peminjaman</font>\n" +
"                          <h3 style=\"margin-top: 4px;\">");
        
        //ID PINJAM
        body.append(getIdPinjam() + "</h3>\n" +
"                          <hr>");
        body.append("<img src=\"cid:image1\" alt=\"\" style=\"display: block; margin-left: auto; margin-right:auto ;\">");
        body.append("<hr>\n" +
"                          <font>Barang </font>\n" +
"                          <h3 style=\"margin-top: 4px;\">");
        body.append(getNamaBarang()+" "+getJml()+" x");
        body.append("</td>\n" +
"                      </tr>\n" +
"                    </table>\n" +
"                  </td>\n" +
"                </tr>\n" +
"\n" +
"                <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"                  <td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 0 0 20px; text-align: center; padding-top: 20px;\" valign=\"top\">\n" +
"										<font>Thanks for using School Management Assets.</font>\n" +
"									</td>\n" +
"                </tr>\n" +
"              </table>\n" +
"            </td>\n" +
"          </tr>\n" +
"        </table>\n" +
"\n" +
"        <div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;\">\n" +
"            <table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"              <tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\">\n" +
"                <td class=\"aligncenter content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; vertical-align: top; color: #999; text-align: center; margin: 0; padding: 0 0 20px;\" align=\"center\" valign=\"top\">\n" +
"                  Questions? Email <a href=\"mailto:\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-decoration: underline; margin: 0;\">schoolmanagementassets@gmail.com</a>\n" +
"                </td>\n" +
"              </tr>\n" +
"            </table>\n" +
"        </div>\n" +
"\n" +
"      </div>\n" +
"		</td>\n" +
"		<td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td>\n" +
"  </tr>\n" +
"</table>\n" +
"</body>");
        
        body.append("</html>");
 
        // inline images
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", "D:/QRCode_Generator/"+getIdPinjam()+".png");
 
        try {
            EmailUtil.send(host, port, myAcount, password, getReceiver(), subject, body.toString(), inlineImages);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
    /**
     * @return the idPinjam
     */
    public String getIdPinjam() {
        return idPinjam;
    }

    /**
     * @param idPinjam the idPinjam to set
     */
    public void setIdPinjam(String idPinjam) {
        this.idPinjam = idPinjam;
    }

    /**
     * @return the namaUser
     */
    public String getNamaUser() {
        return namaUser;
    }

    /**
     * @param namaUser the namaUser to set
     */
    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    /**
     * @return the namaBarang
     */
    public String getNamaBarang() {
        return namaBarang;
    }

    /**
     * @param namaBarang the namaBarang to set
     */
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    /**
     * @return the tglPinjam
     */
    public String getTglPinjam() {
        return tglPinjam;
    }

    /**
     * @param tglPinjam the tglPinjam to set
     */
    public void setTglPinjam(String tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    /**
     * @return the tglExp
     */
    public String getTglExp() {
        return tglExp;
    }

    /**
     * @param tglExp the tglExp to set
     */
    public void setTglExp(String tglExp) {
        this.tglExp = tglExp;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the jml
     */
    public String getJml() {
        return jml;
    }

    /**
     * @param jml the jml to set
     */
    public void setJml(String jml) {
        this.jml = jml;
    }
    
}