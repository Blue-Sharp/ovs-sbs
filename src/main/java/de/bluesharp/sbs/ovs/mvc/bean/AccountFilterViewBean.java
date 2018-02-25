package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static de.bluesharp.sbs.ovs.mvc.bean.AccountTreeNode.AccountTreeNodeType.*;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountFilterViewBean implements Serializable {
    private TreeNode root;

    private TreeNode[] selectedNode;

    @Getter(value = AccessLevel.NONE)
    private final AccountService accountService;

    private final AccountListViewBean accountListViewBean;

    @Autowired
    public AccountFilterViewBean(AccountService accountService, AccountListViewBean accountListViewBean) {
        this.accountService = accountService;
        this.accountListViewBean = accountListViewBean;
    }

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode(new AccountTreeNode("root", null));
        buildTreeNode("authentication.authorities.all", ALL, root);

        TreeNode chairman = buildTreeNode("authentication.authorities.chairman", CHAIRMAN, root);
        TreeNode chairmanFunction1 = new DefaultTreeNode(new AccountTreeNode("Funktion-1", CHAIRMAN), chairman);
        TreeNode chairmanFunction2 = new DefaultTreeNode(new AccountTreeNode("Funktion-2", CHAIRMAN), chairman);
        TreeNode chairmanFunction3 = new DefaultTreeNode(new AccountTreeNode("Funktion-3", CHAIRMAN), chairman);
        TreeNode chairmanFunction4 = new DefaultTreeNode(new AccountTreeNode("Funktion-4", CHAIRMAN), chairman);
        TreeNode chairmanFunction5 = new DefaultTreeNode(new AccountTreeNode("Funktion-5", CHAIRMAN), chairman);
        TreeNode chairmanFunction6 = new DefaultTreeNode(new AccountTreeNode("Funktion-6", CHAIRMAN), chairman);

        TreeNode manager = buildTreeNode("authentication.authorities.manager", MANAGER, root);
        TreeNode managerLocation1 = new DefaultTreeNode(new AccountTreeNode("München", MANAGER), manager);
        TreeNode managerLocation2 = new DefaultTreeNode(new AccountTreeNode("Frankfurt", MANAGER), manager);
        TreeNode managerLocation3 = new DefaultTreeNode(new AccountTreeNode("Berlin", MANAGER), manager);
        TreeNode managerLocation4 = new DefaultTreeNode(new AccountTreeNode("Hamburg", MANAGER), manager);
        TreeNode managerLocation5 = new DefaultTreeNode(new AccountTreeNode("Hannover", MANAGER), manager);
        TreeNode managerLocation6 = new DefaultTreeNode(new AccountTreeNode("Düsseldorf", MANAGER), manager);
        TreeNode managerLocation7 = new DefaultTreeNode(new AccountTreeNode("Essen", MANAGER), manager);
        TreeNode managerLocation8 = new DefaultTreeNode(new AccountTreeNode("Stutgart", MANAGER), manager);
        TreeNode managerLocation9 = new DefaultTreeNode(new AccountTreeNode("Köln", MANAGER), manager);

        TreeNode student = buildTreeNode("authentication.authorities.student", STUDENT, root);
        TreeNode studentsSubject1 = new DefaultTreeNode(new AccountTreeNode("Informatik", STUDENT), student);
        TreeNode studentsSubject2 = new DefaultTreeNode(new AccountTreeNode("Mathematik", STUDENT), student);
        TreeNode studentsSubject3 = new DefaultTreeNode(new AccountTreeNode("Deutsch", STUDENT), student);
        TreeNode studentsSubject4 = new DefaultTreeNode(new AccountTreeNode("Englisch", STUDENT), student);
        TreeNode studentsSubject5 = new DefaultTreeNode(new AccountTreeNode("Französisch", STUDENT), student);
        TreeNode studentsSubject6 = new DefaultTreeNode(new AccountTreeNode("Spanisch", STUDENT), student);

        TreeNode partner = buildTreeNode("authentication.authorities.partner", PARTNER, root);
        TreeNode partnerCriteria1 = new DefaultTreeNode(new AccountTreeNode("Kriterium1", PARTNER), partner);
        TreeNode partnerCriteria2 = new DefaultTreeNode(new AccountTreeNode("Kriterium2", PARTNER), partner);
        TreeNode partnerCriteria3 = new DefaultTreeNode(new AccountTreeNode("Kriterium3", PARTNER), partner);
        TreeNode partnerCriteria4 = new DefaultTreeNode(new AccountTreeNode("Kriterium4", PARTNER), partner);

        TreeNode none = new DefaultTreeNode(
                new AccountTreeNode(getMessageKey("authentication.authorities.none"), NONE), root);
    }

    // TODO RKA Implement rest
    public void updateAccountList() {
        List<Account> result = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(selectedNode)) {
            for (TreeNode n : selectedNode) {
                AccountTreeNode node = (AccountTreeNode) n.getData();
                switch (node.getType()) {
                    case ALL: {
                        result.addAll(accountService.getAccounts());
                        break;
                    }
                    case CHAIRMAN: {
                        result.addAll(accountService.getChairman());
                        break;
                    }
                    case MANAGER: {
                        break;
                    }
                    case STUDENT: {
                        break;
                    }
                    case PARTNER: {
                        break;
                    }
                    case NONE: {
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        accountListViewBean.setAccounts(result);
    }

    private static TreeNode buildTreeNode(String code, AccountTreeNode.AccountTreeNodeType type, TreeNode parent) {
        TreeNode result = new DefaultTreeNode(new AccountTreeNode(getMessageKey(code), type), parent);
        result.setExpanded(true);
        return result;
    }

    private static String getMessageKey(String code) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
        return bundle.getString(code);
    }
}
