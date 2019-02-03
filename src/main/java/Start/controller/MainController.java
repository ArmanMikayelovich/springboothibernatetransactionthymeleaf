package Start.controller;

import Start.dao.BankAccountDao;
import Start.exception.BankTransactionException;
import Start.form.SendMoneyForm;
import Start.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class MainController {
    @Autowired
    private BankAccountDao bankAccountDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccountsModel(Model model) {
        List<BankAccountInfo> list = bankAccountDao.listBankAccountInfo();
        model.addAttribute("accountInfos", list);
        return "accountsPage";

    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
        SendMoneyForm form = new SendMoneyForm(1L, 2L, Double.valueOf(500));
        model.addAttribute("sendMoneyForm", form);
        return "sendMoneyPage";

    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {

        System.out.println("Send Money::" + sendMoneyForm.getAmount());
        try {
            bankAccountDao.sendMoney(sendMoneyForm.getFromAccountId(),
                    sendMoneyForm.getToAccountId(),
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";

        }
        return "redirect:/";


    }
}
