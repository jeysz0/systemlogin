import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Student Login System - Java Swing school project demo.
 * Demo credentials: admin / admin123
 */
public class Main {
    private static final Color NAVY = new Color(20, 38, 76);
    private static final Color BLUE = new Color(48, 91, 180);
    private static final Color PALE = new Color(242, 246, 252);
    private static final Color MUTED = new Color(112, 124, 145);
    private final JFrame frame = new JFrame("Student Login System");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel pages = new JPanel(cardLayout);
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel loginMessage;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) { }
            new Main().show();
        });
    }

    private void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(760, 560));
        frame.setSize(960, 650);
        frame.setLocationRelativeTo(null);
        pages.add(createLoginPage(), "login");
        pages.add(createDashboardPage(), "dashboard");
        frame.setContentPane(pages);
        cardLayout.show(pages, "login");
        frame.setVisible(true);
    }

    private JPanel createLoginPage() {
        JPanel background = new GradientPanel(new Color(20, 38, 76), new Color(48, 91, 180));
        background.setLayout(new GridBagLayout());

        JPanel card = new RoundedPanel(Color.WHITE, 26);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(34, 40, 32, 40));
        card.setPreferredSize(new Dimension(390, 490));

        JLabel icon = new JLabel("S", SwingConstants.CENTER);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        icon.setOpaque(true);
        icon.setBackground(new Color(232, 239, 255));
        icon.setForeground(BLUE);
        icon.setFont(new Font("SansSerif", Font.BOLD, 26));
        icon.setPreferredSize(new Dimension(58, 58));
        icon.setMaximumSize(new Dimension(58, 58));
        icon.setBorder(BorderFactory.createLineBorder(new Color(220, 230, 250), 1, true));

        JLabel title = new JLabel("Student Login System");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        title.setForeground(NAVY);
        JLabel subtitle = new JLabel("Welcome back! Sign in to continue.");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);

        card.add(icon);
        card.add(Box.createVerticalStrut(18));
        card.add(title);
        card.add(Box.createVerticalStrut(7));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(28));
        card.add(fieldLabel("USERNAME"));
        card.add(Box.createVerticalStrut(7));
        usernameField = new JTextField();
        styleTextField(usernameField);
        card.add(usernameField);
        card.add(Box.createVerticalStrut(17));
        card.add(fieldLabel("PASSWORD"));
        card.add(Box.createVerticalStrut(7));

        JPanel passwordRow = new JPanel(new BorderLayout(8, 0));
        passwordRow.setOpaque(false);
        passwordField = new JPasswordField();
        styleTextField(passwordField);
        passwordRow.add(passwordField, BorderLayout.CENTER);
        JButton toggle = new JButton("Show");
        toggle.setFocusPainted(false);
        toggle.setBorderPainted(false);
        toggle.setContentAreaFilled(false);
        toggle.setForeground(BLUE);
        toggle.setFont(new Font("SansSerif", Font.BOLD, 12));
        toggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toggle.addActionListener(e -> {
            if (passwordField.getEchoChar() == (char) 0) {
                passwordField.setEchoChar('\u2022');
                toggle.setText("Show");
            } else {
                passwordField.setEchoChar((char) 0);
                toggle.setText("Hide");
            }
        });
        passwordRow.add(toggle, BorderLayout.EAST);
        passwordRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        card.add(passwordRow);
        card.add(Box.createVerticalStrut(20));

        JButton loginButton = new JButton("Sign In");
        stylePrimaryButton(loginButton);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> login());
        card.add(loginButton);
        passwordField.addActionListener(e -> login());
        usernameField.addActionListener(e -> passwordField.requestFocusInWindow());

        loginMessage = new JLabel(" ", SwingConstants.CENTER);
        loginMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginMessage.setFont(new Font("SansSerif", Font.PLAIN, 12));
        loginMessage.setForeground(new Color(190, 55, 55));
        card.add(Box.createVerticalStrut(10));
        card.add(loginMessage);
        card.add(Box.createVerticalGlue());
        JLabel footer = new JLabel("STUDENT PORTAL  •  DEMO VERSION");
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setForeground(new Color(150, 160, 177));
        footer.setFont(new Font("SansSerif", Font.BOLD, 10));
        card.add(footer);

        background.add(card);
        return background;
    }

    private JPanel createDashboardPage() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(PALE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(20, 34, 20, 34));
        JLabel brand = new JLabel("S  Student Portal");
        brand.setFont(new Font("SansSerif", Font.BOLD, 19));
        brand.setForeground(NAVY);
        JButton logout = new JButton("Log Out");
        styleOutlineButton(logout);
        logout.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            loginMessage.setText(" ");
            cardLayout.show(pages, "login");
        });
        header.add(brand, BorderLayout.WEST);
        header.add(logout, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(38, 48, 40, 48));

        JLabel eyebrow = new JLabel("YOUR WORKSPACE");
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 11));
        eyebrow.setForeground(BLUE);
        JLabel title = new JLabel("Student Group Information");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(NAVY);
        JLabel welcome = new JLabel("Welcome, admin! Here are your group members.");
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 15));
        welcome.setForeground(MUTED);
        content.add(eyebrow);
        content.add(Box.createVerticalStrut(8));
        content.add(title);
        content.add(Box.createVerticalStrut(8));
        content.add(welcome);
        content.add(Box.createVerticalStrut(30));

        JPanel sectionHeader = new JPanel(new BorderLayout());
        sectionHeader.setOpaque(false);
        JLabel section = new JLabel("Group Members");
        section.setFont(new Font("SansSerif", Font.BOLD, 18));
        section.setForeground(NAVY);
        JLabel count = new JLabel("3 MEMBERS");
        count.setFont(new Font("SansSerif", Font.BOLD, 11));
        count.setForeground(MUTED);
        sectionHeader.add(section, BorderLayout.WEST);
        sectionHeader.add(count, BorderLayout.EAST);
        sectionHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        content.add(sectionHeader);
        content.add(Box.createVerticalStrut(14));

        String[] members = {"Jeffy Lean D. Pidor", "Jhon Paul Libay", "Christianiel Baluya"};
        String[] initials = {"JP", "JL", "CB"};
        Color[] avatarColors = {new Color(225, 235, 255), new Color(226, 244, 239), new Color(250, 235, 219)};
        Color[] textColors = {new Color(48, 91, 180), new Color(35, 125, 99), new Color(166, 104, 35)};
        for (int i = 0; i < members.length; i++) {
            content.add(memberCard(initials[i], members[i], avatarColors[i], textColors[i]));
            content.add(Box.createVerticalStrut(12));
        }
        content.add(Box.createVerticalGlue());
        JLabel note = new JLabel("Student Login System  •  Group directory");
        note.setForeground(new Color(145, 155, 171));
        note.setFont(new Font("SansSerif", Font.PLAIN, 12));
        content.add(note);

        root.add(content, BorderLayout.CENTER);
        return root;
    }

    private JPanel memberCard(String initials, String name, Color avatarBg, Color avatarFg) {
        JPanel card = new RoundedPanel(Color.WHITE, 16);
        card.setLayout(new BorderLayout(16, 0));
        card.setBorder(new EmptyBorder(15, 18, 15, 18));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 78));
        JLabel avatar = new JLabel(initials, SwingConstants.CENTER);
        avatar.setOpaque(true);
        avatar.setBackground(avatarBg);
        avatar.setForeground(avatarFg);
        avatar.setFont(new Font("SansSerif", Font.BOLD, 14));
        avatar.setPreferredSize(new Dimension(46, 46));
        JLabel memberName = new JLabel(name);
        memberName.setFont(new Font("SansSerif", Font.BOLD, 15));
        memberName.setForeground(NAVY);
        JLabel role = new JLabel("Group member");
        role.setFont(new Font("SansSerif", Font.PLAIN, 12));
        role.setForeground(MUTED);
        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.add(memberName);
        labels.add(Box.createVerticalStrut(5));
        labels.add(role);
        card.add(avatar, BorderLayout.WEST);
        card.add(labels, BorderLayout.CENTER);
        return card;
    }

    private JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 11));
        label.setForeground(new Color(78, 91, 113));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(NAVY);
        field.setBackground(new Color(248, 250, 253));
        field.setCaretColor(BLUE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 229, 240), 1, true),
            new EmptyBorder(10, 12, 10, 12)));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        field.setPreferredSize(new Dimension(300, 44));
    }

    private void stylePrimaryButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        button.setPreferredSize(new Dimension(300, 46));
        button.setOpaque(true);
    }

    private void styleOutlineButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(BLUE);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(211, 222, 242), 1, true),
            new EmptyBorder(9, 16, 9, 16)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            loginMessage.setForeground(new Color(190, 105, 25));
            loginMessage.setText("Please enter both username and password.");
            return;
        }
        // Demo-only authentication. Do not hard-code credentials in production systems.
        if (username.equals("admin") && password.equals("admin123")) {
            loginMessage.setForeground(new Color(35, 125, 99));
            loginMessage.setText("Login successful!");
            cardLayout.show(pages, "dashboard");
        } else {
            loginMessage.setForeground(new Color(190, 55, 55));
            loginMessage.setText("Incorrect username or password. Try again.");
            passwordField.setText("");
            passwordField.requestFocusInWindow();
        }
    }

    /** Rounded white surface used for cards. */
    private static class RoundedPanel extends JPanel {
        private final Color fill;
        private final int radius;
        RoundedPanel(Color fill, int radius) {
            this.fill = fill;
            this.radius = radius;
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(10, 25, 55, 24));
            g.fill(new RoundRectangle2D.Float(2, 4, getWidth() - 4, getHeight() - 6, radius, radius));
            g.setColor(fill);
            g.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 4, getHeight() - 6, radius, radius));
            g.dispose();
            super.paintComponent(graphics);
        }
    }

    /** Subtle diagonal gradient background for the login screen. */
    private static class GradientPanel extends JPanel {
        private final Color start, end;
        GradientPanel(Color start, Color end) { this.start = start; this.end = end; }
        @Override protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setPaint(new GradientPaint(0, 0, start, getWidth(), getHeight(), end));
            g.fillRect(0, 0, getWidth(), getHeight());
            // Low-key decorative circles keep the page polished without distracting from the form.
            g.setColor(new Color(255, 255, 255, 18));
            g.fillOval(getWidth() - 180, 35, 230, 230);
            g.fillOval(-90, getHeight() - 170, 230, 230);
            g.dispose();
        }
    }
}
