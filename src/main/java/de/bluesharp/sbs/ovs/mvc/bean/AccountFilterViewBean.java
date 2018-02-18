package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ResourceBundle;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountFilterViewBean implements Serializable {

    private TreeNode root;

    private TreeNode selectedNode;

    @Getter(value = AccessLevel.NONE)
    private final AccountService accountService;

    @Autowired
    public AccountFilterViewBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {


        root = new DefaultTreeNode(new AccountTreeNode("root"));

        TreeNode all = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.all")), root);
        all.setExpanded(true);

        TreeNode admins = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.admin")), root);

        TreeNode chairman = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.chairman")), root);
        chairman.setExpanded(true);
        TreeNode chairmanFunction1 = new DefaultTreeNode(new AccountTreeNode("Funktion-1"), chairman);
        TreeNode chairmanFunction2 = new DefaultTreeNode(new AccountTreeNode("Funktion-2"), chairman);
        TreeNode chairmanFunction3 = new DefaultTreeNode(new AccountTreeNode("Funktion-3"), chairman);
        TreeNode chairmanFunction4 = new DefaultTreeNode(new AccountTreeNode("Funktion-4"), chairman);
        TreeNode chairmanFunction5 = new DefaultTreeNode(new AccountTreeNode("Funktion-5"), chairman);
        TreeNode chairmanFunction6 = new DefaultTreeNode(new AccountTreeNode("Funktion-6"), chairman);

        TreeNode manager = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.manager")), root);
        manager.setExpanded(true);
        TreeNode managerLocation1 = new DefaultTreeNode(new AccountTreeNode("München"), manager);
        TreeNode managerLocation2 = new DefaultTreeNode(new AccountTreeNode("Frankfurt"), manager);
        TreeNode managerLocation3 = new DefaultTreeNode(new AccountTreeNode("Berlin"), manager);
        TreeNode managerLocation4 = new DefaultTreeNode(new AccountTreeNode("Hamburg"), manager);
        TreeNode managerLocation5 = new DefaultTreeNode(new AccountTreeNode("Hannover"), manager);
        TreeNode managerLocation6 = new DefaultTreeNode(new AccountTreeNode("Düsseldorf"), manager);
        TreeNode managerLocation7 = new DefaultTreeNode(new AccountTreeNode("Essen"), manager);
        TreeNode managerLocation8 = new DefaultTreeNode(new AccountTreeNode("Stutgart"), manager);
        TreeNode managerLocation9 = new DefaultTreeNode(new AccountTreeNode("Köln"), manager);

        TreeNode student = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.student")), root);
        student.setExpanded(true);
        TreeNode studentsSubject1 = new DefaultTreeNode(new AccountTreeNode("Informatik"), student);
        TreeNode studentsSubject2 = new DefaultTreeNode(new AccountTreeNode("Mathematik"), student);
        TreeNode studentsSubject3 = new DefaultTreeNode(new AccountTreeNode("Deutsch"), student);
        TreeNode studentsSubject4 = new DefaultTreeNode(new AccountTreeNode("Englisch"), student);
        TreeNode studentsSubject5 = new DefaultTreeNode(new AccountTreeNode("Französisch"), student);
        TreeNode studentsSubject6 = new DefaultTreeNode(new AccountTreeNode("Spanisch"), student);

        TreeNode partner = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.partner")), root);
        partner.setExpanded(true);
        TreeNode partnerCriteria1 = new DefaultTreeNode(new AccountTreeNode("Kriterium1"), partner);
        TreeNode partnerCriteria2 = new DefaultTreeNode(new AccountTreeNode("Kriterium2"), partner);
        TreeNode partnerCriteria3 = new DefaultTreeNode(new AccountTreeNode("Kriterium3"), partner);
        TreeNode partnerCriteria4 = new DefaultTreeNode(new AccountTreeNode("Kriterium4"), partner);

        TreeNode none = new DefaultTreeNode(new AccountTreeNode(
                getMessageKey("authentication.authorities.none")), root);
    }

    public void displaySelectedSingle() {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private String getMessageKey(@NotNull String code) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle =facesContext.getApplication().getResourceBundle(facesContext, "msg");
        return bundle.getString(code);
    }
}
