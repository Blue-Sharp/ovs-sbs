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

import static de.bluesharp.sbs.ovs.mvc.bean.AccountTreeNodeSupportBean.AccountTreeNodeType.*;

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
    private void init() {
        root = new DefaultTreeNode(new AccountTreeNodeSupportBean("root", null));
        buildTreeNode("authentication.authorities.all", ALL, root);

        TreeNode chairman = buildTreeNode("authentication.authorities.chairman", CHAIRMAN, root);
        TreeNode chairmanFunction1 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-1", CHAIRMAN), chairman);
        TreeNode chairmanFunction2 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-2", CHAIRMAN), chairman);
        TreeNode chairmanFunction3 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-3", CHAIRMAN), chairman);
        TreeNode chairmanFunction4 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-4", CHAIRMAN), chairman);
        TreeNode chairmanFunction5 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-5", CHAIRMAN), chairman);
        TreeNode chairmanFunction6 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Funktion-6", CHAIRMAN), chairman);

        TreeNode manager = buildTreeNode("authentication.authorities.manager", MANAGER, root);
        TreeNode managerLocation1 = new DefaultTreeNode(new AccountTreeNodeSupportBean("München", MANAGER), manager);
        TreeNode managerLocation2 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Frankfurt", MANAGER), manager);
        TreeNode managerLocation3 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Berlin", MANAGER), manager);
        TreeNode managerLocation4 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Hamburg", MANAGER), manager);
        TreeNode managerLocation5 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Hannover", MANAGER), manager);
        TreeNode managerLocation6 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Düsseldorf", MANAGER), manager);
        TreeNode managerLocation7 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Essen", MANAGER), manager);
        TreeNode managerLocation8 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Stutgart", MANAGER), manager);
        TreeNode managerLocation9 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Köln", MANAGER), manager);

        TreeNode student = buildTreeNode("authentication.authorities.student", STUDENT, root);
        TreeNode studentsSubject1 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Informatik", STUDENT), student);
        TreeNode studentsSubject2 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Mathematik", STUDENT), student);
        TreeNode studentsSubject3 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Deutsch", STUDENT), student);
        TreeNode studentsSubject4 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Englisch", STUDENT), student);
        TreeNode studentsSubject5 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Französisch", STUDENT), student);
        TreeNode studentsSubject6 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Spanisch", STUDENT), student);

        TreeNode partner = buildTreeNode("authentication.authorities.partner", PARTNER, root);
        TreeNode partnerCriteria1 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Kriterium1", PARTNER), partner);
        TreeNode partnerCriteria2 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Kriterium2", PARTNER), partner);
        TreeNode partnerCriteria3 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Kriterium3", PARTNER), partner);
        TreeNode partnerCriteria4 = new DefaultTreeNode(new AccountTreeNodeSupportBean("Kriterium4", PARTNER), partner);

        TreeNode none = new DefaultTreeNode(
                new AccountTreeNodeSupportBean(getMessageKey("authentication.authorities.none"), NONE), root);
    }

    // TODO RKA Implement rest
    public void updateAccountList() {
        List<Account> result = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(selectedNode)) {
            for (TreeNode n : selectedNode) {
                AccountTreeNodeSupportBean node = (AccountTreeNodeSupportBean) n.getData();
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

    private static TreeNode buildTreeNode(String code, AccountTreeNodeSupportBean.AccountTreeNodeType type, TreeNode parent) {
        TreeNode result = new DefaultTreeNode(new AccountTreeNodeSupportBean(getMessageKey(code), type), parent);
        result.setExpanded(true);
        return result;
    }

    private static String getMessageKey(String code) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
        return bundle.getString(code);
    }
}
