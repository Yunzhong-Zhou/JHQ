package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-12-24.
 */
public class PredictionDetailModel implements Serializable {
    /**
     * trading_point : {"symbol":"btcusdt","status":1,"timestamp":1577109001,"price":7589.9,"created_at":"2019-12-24 00:08:21"}
     * kline : {"symbol":"btcusdt","timestamp":1577116800,"high":7585.66,"close":7583.62,"open":7584.34,"low":7583.62,"volume":49.4482,"support":7530.38,"resistence":"7641.25556934686","created_at":"2019-12-24 00:07:27"}
     * kline_list : [{"symbol":"btcusdt","timestamp":1577116800,"high":7585.66,"close":7583.62,"open":7584.34,"low":7583.62,"volume":49.4482,"support":7530.38,"resistence":"7641.25556934686","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577113200,"high":7610,"close":7583.62,"open":7575.59,"low":7548.12,"volume":2684.06,"support":7521.75,"resistence":"7641.248755868565","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577109600,"high":7580,"close":7574.43,"open":7517.6,"low":7480,"volume":2471.27,"support":7512.48,"resistence":"7639.864113633437","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577106000,"high":7593,"close":7517.6,"open":7559.03,"low":7500,"volume":2044.38,"support":7503.31,"resistence":"7637.727503395482","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577102400,"high":7566.17,"close":7560.49,"open":7553.03,"low":7532.04,"volume":1021.93,"support":7335.47,"resistence":"7493.08850000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577098800,"high":7561.36,"close":7552.96,"open":7521.28,"low":7505,"volume":1638.62,"support":7283.49,"resistence":"7476.314500000013","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577095200,"high":7544,"close":7522.58,"open":7531.61,"low":7510.6,"volume":690.72,"support":7230.98,"resistence":"7457.9640000000145","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577091600,"high":7533.73,"close":7531.62,"open":7522.96,"low":7495,"volume":730.734,"support":7182.74,"resistence":"7440.2140000000145","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577088000,"high":7540,"close":7523.6,"open":7494.9,"low":7488,"volume":876.964,"support":7142.53,"resistence":"7422.033000000015","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577084400,"high":7510.17,"close":7494.89,"open":7474.49,"low":7466.53,"volume":1166.68,"support":7108.26,"resistence":"7404.288000000015","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577080800,"high":7549.75,"close":7472.79,"open":7543.37,"low":7447.29,"volume":2591.93,"support":7077.04,"resistence":"7387.704000000014","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577077200,"high":7579,"close":7543.37,"open":7556.62,"low":7530,"volume":1240.53,"support":7051.23,"resistence":"7372.7310000000125","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577073600,"high":7593.28,"close":7556.5,"open":7589.73,"low":7555.68,"volume":693.681,"support":7353.66,"resistence":"7677.642983069586","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577070000,"high":7597.82,"close":7590.16,"open":7560.11,"low":7560.11,"volume":2025.32,"support":7333.81,"resistence":"7654.282870863011","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577066400,"high":7631.46,"close":7560.32,"open":7603.15,"low":7548.84,"volume":1981.46,"support":7313.5,"resistence":"7617.474673932676","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577062800,"high":7630,"close":7603.16,"open":7543.68,"low":7535.81,"volume":2369.97,"support":7291.98,"resistence":"7583.712318299744","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577059200,"high":7622.83,"close":7544.63,"open":7497.81,"low":7497.8,"volume":4866.74,"support":7268.36,"resistence":"7530.480941917169","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577055600,"high":7500,"close":7497.81,"open":7398.03,"low":7397.98,"volume":3063.53,"support":7247.98,"resistence":"7482.985898448355","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577052000,"high":7412.27,"close":7398.03,"open":7395.5,"low":7375.25,"volume":761.338,"support":7229.9,"resistence":"7439.511211279751","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577048400,"high":7418.36,"close":7395.51,"open":7410.97,"low":7377.12,"volume":716.136,"support":7217.18,"resistence":"7414.976982423788","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577044800,"high":7451,"close":7410.97,"open":7397.13,"low":7385,"volume":1172.77,"support":7204.63,"resistence":"7386.809797980489","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577041200,"high":7450,"close":7398.1,"open":7391.89,"low":7359.83,"volume":2432.19,"support":7190.69,"resistence":"7348.641662688207","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577037600,"high":7423,"close":7391.17,"open":7313.06,"low":7311.25,"volume":2466.2,"support":7177.48,"resistence":"7305.116313523927","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577034000,"high":7373.52,"close":7313.11,"open":7226.04,"low":7209.06,"volume":3918.28,"support":7164.84,"resistence":"7247.454807216174","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577030400,"high":7260,"close":7225.01,"open":7185.09,"low":7163.26,"volume":3253.31,"support":7155.77,"resistence":"7203.925791433206","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577026800,"high":7195,"close":7185.95,"open":7167.58,"low":7167.58,"volume":1006.62,"support":7152.19,"resistence":"7188.383487193981","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577023200,"high":7177.5,"close":7167.58,"open":7168,"low":7160.48,"volume":389.422,"support":7150.02,"resistence":"7182.910828416714","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577019600,"high":7194.05,"close":7168,"open":7168.75,"low":7156.5,"volume":968.362,"support":7149.06,"resistence":"7180.94465910692","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577016000,"high":7174.2,"close":7168.7,"open":7163.17,"low":7158.88,"volume":359.365,"support":7148.16,"resistence":"7178.848287995077","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577012400,"high":7179.99,"close":7163.21,"open":7173.42,"low":7153.18,"volume":387.496,"support":7146.88,"resistence":"7176.138552789784","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577008800,"high":7188.66,"close":7173.33,"open":7162.02,"low":7161.58,"volume":874.598,"support":7145.69,"resistence":"7174.119921095365","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577005200,"high":7169,"close":7162.02,"open":7159.42,"low":7157.38,"volume":296.438,"support":7143.32,"resistence":"7169.989336585163","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1577001600,"high":7200,"close":7159.43,"open":7183.97,"low":7140,"volume":1382.43,"support":7141.62,"resistence":"7167.636362530912","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576998000,"high":7194,"close":7183.97,"open":7129.83,"low":7129.83,"volume":1604.71,"support":7140.96,"resistence":"7165.775886093213","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576994400,"high":7135.89,"close":7129.83,"open":7130.76,"low":7123.68,"volume":468.181,"support":7123.54,"resistence":"7139.185500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576990800,"high":7141,"close":7130.76,"open":7137.15,"low":7128.38,"volume":1110.05,"support":7124.6,"resistence":"7139.643500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576987200,"high":7144.67,"close":7137.14,"open":7136.15,"low":7135.15,"volume":399.236,"support":7139.07,"resistence":"7156.148200887423","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576983600,"high":7147.71,"close":7136.15,"open":7143.93,"low":7132.8,"volume":259.742,"support":7139.24,"resistence":"7156.297130224624","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576980000,"high":7147.31,"close":7143.72,"open":7144.55,"low":7138.15,"volume":366.792,"support":7139.03,"resistence":"7156.334284609522","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576976400,"high":7144.55,"close":7144.55,"open":7132,"low":7131.99,"volume":174.519,"support":7138.93,"resistence":"7156.147596789211","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576972800,"high":7133.94,"close":7131.99,"open":7133.94,"low":7125,"volume":187.279,"support":7139.43,"resistence":"7157.806368148399","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576969200,"high":7140,"close":7134.06,"open":7138.14,"low":7123.35,"volume":196.728,"support":7140.17,"resistence":"7158.492903770278","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576965600,"high":7139.64,"close":7138.38,"open":7131.36,"low":7123.8,"volume":378.797,"support":7141.25,"resistence":"7160.510165824589","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576962000,"high":7177.6,"close":7131.69,"open":7153.41,"low":7119.47,"volume":845.754,"support":7142.67,"resistence":"7164.855560294244","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576958400,"high":7153.4,"close":7153.4,"open":7142.56,"low":7136.96,"volume":876.893,"support":7143.91,"resistence":"7166.271861729903","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576954800,"high":7148.75,"close":7142.56,"open":7148.25,"low":7136.98,"volume":267.859,"support":7144.46,"resistence":"7168.225480504938","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576951200,"high":7151.74,"close":7148.25,"open":7149.99,"low":7145,"volume":236.423,"support":7146.71,"resistence":"7176.983887683949","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576947600,"high":7153.15,"close":7149.99,"open":7143.14,"low":7141.71,"volume":635.097,"support":7148.36,"resistence":"7182.179001925528","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576944000,"high":7143.4,"close":7143.19,"open":7139.47,"low":7127.9,"volume":243.461,"support":7111.96,"resistence":"7150.370500000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576940400,"high":7143.91,"close":7139.49,"open":7125.73,"low":7123.1,"volume":352.206,"support":7108.8,"resistence":"7153.367000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576936800,"high":7142.6,"close":7125.91,"open":7127.94,"low":7118.06,"volume":297.188,"support":7109.67,"resistence":"7155.54000000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576933200,"high":7147.7,"close":7127.94,"open":7146.82,"low":7118.1,"volume":418.738,"support":7112.64,"resistence":"7158.754500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576929600,"high":7150.65,"close":7146.21,"open":7148.46,"low":7135.09,"volume":404.478,"support":7116.75,"resistence":"7161.095000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576926000,"high":7153.76,"close":7148.5,"open":7139,"low":7130.91,"volume":491.56,"support":7118.21,"resistence":"7162.043500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576922400,"high":7139,"close":7138.99,"open":7119.36,"low":7118.76,"volume":434.253,"support":7118.79,"resistence":"7162.3355000000065","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576918800,"high":7141.06,"close":7119.36,"open":7140.55,"low":7110,"volume":755.113,"support":7120.82,"resistence":"7165.094500000011","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576915200,"high":7142.84,"close":7140.41,"open":7131.78,"low":7130.83,"volume":320.514,"support":7128.77,"resistence":"7168.076500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576911600,"high":7144.47,"close":7131.99,"open":7141.72,"low":7124.75,"volume":515.664,"support":7132.51,"resistence":"7170.291000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576908000,"high":7160.3,"close":7141.72,"open":7154.86,"low":7139.48,"volume":448.27,"support":7136.33,"resistence":"7171.182500000013","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576904400,"high":7160,"close":7154.52,"open":7147.12,"low":7141,"volume":254.228,"support":7136.7,"resistence":"7171.267000000013","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576900800,"high":7176.85,"close":7146.89,"open":7155.57,"low":7142.91,"volume":909.414,"support":7132.36,"resistence":"7170.17950000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576897200,"high":7167.36,"close":7155.58,"open":7166.84,"low":7150,"volume":1283.21,"support":7129.46,"resistence":"7169.47100000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576893600,"high":7172.97,"close":7166.83,"open":7156.52,"low":7151.9,"volume":637.076,"support":7127.93,"resistence":"7168.937500000013","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576890000,"high":7170.62,"close":7156.48,"open":7164.38,"low":7144.23,"volume":574.608,"support":7116.47,"resistence":"7165.88450000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576886400,"high":7187.62,"close":7164.4,"open":7187.62,"low":7154.11,"volume":507.328,"support":7103.2,"resistence":"7162.571000000013","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576882800,"high":7188.96,"close":7187.62,"open":7181.25,"low":7167.6,"volume":210.488,"support":7160.51,"resistence":"7222.305895614526","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576879200,"high":7196.69,"close":7181.25,"open":7190.13,"low":7172.71,"volume":275.159,"support":7157.73,"resistence":"7219.394690040467","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576875600,"high":7205,"close":7190.13,"open":7202.72,"low":7183.16,"volume":367.994,"support":7154.83,"resistence":"7217.252173155086","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576872000,"high":7210,"close":7203.12,"open":7182.94,"low":7180,"volume":661.308,"support":7150.45,"resistence":"7214.623283014832","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576868400,"high":7203.77,"close":7182.95,"open":7190.77,"low":7173.49,"volume":578.739,"support":7146.54,"resistence":"7206.808449782165","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576864800,"high":7204.96,"close":7190.2,"open":7174.7,"low":7166.83,"volume":951.913,"support":7144.86,"resistence":"7202.809311831048","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576861200,"high":7187.3,"close":7174.75,"open":7165.32,"low":7146.3,"volume":829.095,"support":7143.26,"resistence":"7197.764693601914","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576857600,"high":7170.4,"close":7165.18,"open":7154.79,"low":7131.77,"volume":828.387,"support":7142.28,"resistence":"7195.164350882338","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576854000,"high":7214.85,"close":7154.34,"open":7194.16,"low":7135.05,"volume":1347.68,"support":7141.5,"resistence":"7193.466390404473","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576850400,"high":7200,"close":7194.17,"open":7179,"low":7150.24,"volume":1047.47,"support":7142.58,"resistence":"7196.44294331713","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576846800,"high":7185.8,"close":7179,"open":7184.7,"low":7160,"volume":812.153,"support":7140.7,"resistence":"7189.615195831042","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576843200,"high":7192.57,"close":7184.7,"open":7149.64,"low":7137.85,"volume":1588.43,"support":7138.31,"resistence":"7184.078003390378","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576839600,"high":7157.38,"close":7149.82,"open":7143.38,"low":7135.03,"volume":644.724,"support":7135.96,"resistence":"7176.480261032846","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576836000,"high":7148,"close":7143.41,"open":7132.07,"low":7130.77,"volume":424.737,"support":7135.7,"resistence":"7175.9249564313195","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576832400,"high":7158.24,"close":7132.77,"open":7133.79,"low":7126.36,"volume":806.15,"support":7134.68,"resistence":"7175.105153835586","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576828800,"high":7175,"close":7132.72,"open":7145.74,"low":7095.36,"volume":1269.73,"support":7132.54,"resistence":"7177.414823662443","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576825200,"high":7159,"close":7144.91,"open":7105.56,"low":7104.15,"volume":1055.25,"support":7135.26,"resistence":"7186.0224804336685","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576821600,"high":7129.76,"close":7105.77,"open":7087.36,"low":7082.86,"volume":1341.17,"support":7084.5,"resistence":"7135.538500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576818000,"high":7135.48,"close":7090.21,"open":7123.09,"low":7087.36,"volume":1187.75,"support":7088.24,"resistence":"7137.884000000004","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576814400,"high":7139.37,"close":7123.09,"open":7132.97,"low":7121,"volume":233.542,"support":7093.93,"resistence":"7139.372500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576810800,"high":7141.39,"close":7132.15,"open":7123.24,"low":7118.4,"volume":399.935,"support":7085.09,"resistence":"7137.185000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576807200,"high":7138.76,"close":7123.18,"open":7102.49,"low":7097.84,"volume":985.764,"support":7079.73,"resistence":"7135.237000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576803600,"high":7130.89,"close":7102.48,"open":7125,"low":7093.19,"volume":1024.8,"support":7080.63,"resistence":"7135.855000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576800000,"high":7161.32,"close":7125,"open":7149.44,"low":7080.01,"volume":1784.76,"support":7084.84,"resistence":"7138.037000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576796400,"high":7161.9,"close":7149.44,"open":7157.02,"low":7138,"volume":807.641,"support":7085.9,"resistence":"7138.752000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576792800,"high":7163.26,"close":7158.06,"open":7155.08,"low":7131.73,"volume":441.286,"support":7085.82,"resistence":"7139.250000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576789200,"high":7169.13,"close":7155.14,"open":7149.57,"low":7128.12,"volume":669.966,"support":7085.85,"resistence":"7138.777000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576785600,"high":7179.81,"close":7149.63,"open":7176,"low":7138.43,"volume":673.605,"support":7085.8,"resistence":"7138.934500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576782000,"high":7182.81,"close":7176,"open":7156.41,"low":7138.33,"volume":601.027,"support":7075.39,"resistence":"7143.245500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576778400,"high":7167.19,"close":7156.46,"open":7131.32,"low":7119.21,"volume":822.426,"support":7059.66,"resistence":"7148.283000000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576774800,"high":7157.88,"close":7131.33,"open":7137.62,"low":7111.37,"volume":860.7,"support":7013.98,"resistence":"7161.369500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576771200,"high":7173.33,"close":7137.61,"open":7144.59,"low":7119.35,"volume":957.998,"support":7013.38,"resistence":"7161.054500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576767600,"high":7172.71,"close":7144.6,"open":7123.13,"low":7110.02,"volume":1400.83,"support":7010.64,"resistence":"7159.675000000006","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576764000,"high":7123,"close":7123,"open":7089,"low":7072,"volume":1858.32,"support":6975.7,"resistence":"7150.057500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576760400,"high":7231.88,"close":7090.06,"open":7187.03,"low":7068.43,"volume":4233.07,"support":6921.1,"resistence":"7136.838500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576756800,"high":7215.33,"close":7187,"open":7151.01,"low":7140.35,"volume":2414.59,"support":6878.54,"resistence":"7125.333000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576753200,"high":7179.45,"close":7150.56,"open":7152.67,"low":7127.77,"volume":1372.5,"support":6842.51,"resistence":"7109.858000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576749600,"high":7200,"close":7152.68,"open":7120.39,"low":7120.27,"volume":2455.92,"support":6799.19,"resistence":"7093.452500000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576746000,"high":7157.47,"close":7119.98,"open":7079.34,"low":7043,"volume":3121.5,"support":6730.87,"resistence":"7070.6090000000095","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576742400,"high":7117.4,"close":7079.34,"open":7093.19,"low":7078.65,"volume":1657.26,"support":6668.01,"resistence":"7048.2515000000085","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576738800,"high":7141.3,"close":7093.19,"open":7135.39,"low":7087.21,"volume":1489.84,"support":6582.34,"resistence":"7021.384500000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576735200,"high":7168.63,"close":7135.54,"open":7146.43,"low":7128,"volume":1360.48,"support":6526.15,"resistence":"6997.55700000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576731600,"high":7167.54,"close":7146.12,"open":7139.3,"low":7105.36,"volume":1564.5,"support":6484.07,"resistence":"6973.530000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576728000,"high":7160,"close":7139.3,"open":7158.57,"low":7130.15,"volume":1121.48,"support":6947.9,"resistence":"7451.996492643506","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576724400,"high":7177.47,"close":7159.4,"open":7149.95,"low":7148.34,"volume":1156.46,"support":6922.13,"resistence":"7437.033199636244","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576720800,"high":7184.4,"close":7148.6,"open":7158.42,"low":7127,"volume":1707.11,"support":6895.14,"resistence":"7414.036169538784","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576717200,"high":7235.81,"close":7158.29,"open":7235.61,"low":7123.59,"volume":3617.85,"support":6869.08,"resistence":"7386.790087411999","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576713600,"high":7363.88,"close":7235.85,"open":7276.84,"low":7200,"volume":4523.49,"support":6844.67,"resistence":"7351.457009959145","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576710000,"high":7421.95,"close":7276.75,"open":7416.5,"low":7225.98,"volume":6468.53,"support":6816.68,"resistence":"7294.9899487971325","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576706400,"high":7437.05,"close":7418.19,"open":7124.65,"low":7124.18,"volume":5020.33,"support":6787.17,"resistence":"7218.853346124245","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576702800,"high":7195.74,"close":7125.03,"open":7110.02,"low":7103.48,"volume":2815.16,"support":6751.39,"resistence":"7072.3642970568235","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576699200,"high":7200,"close":7110.02,"open":6952.94,"low":6938.05,"volume":7463.04,"support":6728.14,"resistence":"7001.288941444783","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576695600,"high":6960.01,"close":6952.25,"open":6858.84,"low":6852.45,"volume":3001.52,"support":6705.45,"resistence":"6916.208560034922","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576692000,"high":6875.47,"close":6858.62,"open":6859.96,"low":6820.88,"volume":1049.62,"support":6689,"resistence":"6869.29797200071","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576688400,"high":6917.77,"close":6859.95,"open":6877.51,"low":6825.01,"volume":1910.27,"support":6675.45,"resistence":"6842.998020467537","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576684800,"high":6903.18,"close":6877.5,"open":6823.13,"low":6771.99,"volume":3523.67,"support":6663.27,"resistence":"6809.453750104257","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576681200,"high":6863.26,"close":6822.45,"open":6696.58,"low":6696.03,"volume":5693.42,"support":6648.61,"resistence":"6760.7722386468","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576677600,"high":6725.96,"close":6695.81,"open":6672.39,"low":6633.91,"volume":4862.75,"support":6638.48,"resistence":"6717.7974339554","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576674000,"high":6685.27,"close":6672.83,"open":6542.39,"low":6433,"volume":11103.2,"support":6557.62,"resistence":"6639.191500000008","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576670400,"high":6628.71,"close":6542,"open":6616.14,"low":6500,"volume":3904.42,"support":6554.92,"resistence":"6641.051000000009","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576666800,"high":6658,"close":6616.64,"open":6655.11,"low":6606.53,"volume":952.307,"support":6650.83,"resistence":"6734.121528846388","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576663200,"high":6666.49,"close":6655,"open":6633.46,"low":6621.24,"volume":1494.67,"support":6654.75,"resistence":"6738.609177667472","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576659600,"high":6655.38,"close":6633.46,"open":6623.89,"low":6619.98,"volume":875.82,"support":6659.59,"resistence":"6753.510223905812","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576656000,"high":6653.88,"close":6623.89,"open":6619.98,"low":6610,"volume":1007.36,"support":6666.6,"resistence":"6771.905675253381","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576652400,"high":6633.79,"close":6619.65,"open":6627.43,"low":6591.83,"volume":1535.51,"support":6679.35,"resistence":"6817.508329913981","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576648800,"high":6683.77,"close":6627.43,"open":6670,"low":6614.59,"volume":1340.86,"support":6695.05,"resistence":"6869.220093755713","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576645200,"high":6685,"close":6670,"open":6676.11,"low":6651.2,"volume":1118.8,"support":6708.06,"resistence":"6898.193976528773","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576641600,"high":6713.89,"close":6676.1,"open":6686.48,"low":6657.89,"volume":1047.2,"support":6718.66,"resistence":"6922.28038354859","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576638000,"high":6724.48,"close":6686.48,"open":6702.69,"low":6681.27,"volume":1506.41,"support":6728.82,"resistence":"6942.9438022540635","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576634400,"high":6710,"close":6702.72,"open":6661.24,"low":6647.08,"volume":1819.79,"support":6738.34,"resistence":"6960.878433415361","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576630800,"high":6688,"close":6660.02,"open":6655.51,"low":6643.38,"volume":1474.98,"support":6516.49,"resistence":"6747.6250000000055","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576627200,"high":6658.15,"close":6656.28,"open":6623.43,"low":6600,"volume":1036.41,"support":6523.77,"resistence":"6759.089500000004","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576623600,"high":6636.18,"close":6623.13,"open":6587.56,"low":6560,"volume":1510.16,"support":6534.02,"resistence":"6770.580000000004","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576620000,"high":6689.14,"close":6587.72,"open":6616.55,"low":6570,"volume":2505.31,"support":6552.23,"resistence":"6783.736500000006","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576616400,"high":6623.4,"close":6616.33,"open":6584.09,"low":6577.81,"volume":961.374,"support":6581.35,"resistence":"6799.016500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576612800,"high":6645.04,"close":6584.17,"open":6619.14,"low":6568.97,"volume":2475.84,"support":6608.83,"resistence":"6812.450000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576609200,"high":6713.45,"close":6619.98,"open":6710.58,"low":6591.36,"volume":4225.91,"support":6650.65,"resistence":"6828.068500000004","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576605600,"high":6728.42,"close":6710,"open":6710.04,"low":6701.17,"volume":921.261,"support":6690.35,"resistence":"6841.682500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576602000,"high":6743.18,"close":6710.02,"open":6737.6,"low":6691.64,"volume":1070.7,"support":6710.75,"resistence":"6850.798500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576598400,"high":6741.59,"close":6737.6,"open":6696.8,"low":6645,"volume":3852.1,"support":6734.76,"resistence":"6859.657500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576594800,"high":6757.7,"close":6695.01,"open":6751.78,"low":6695,"volume":1394.46,"support":6754.24,"resistence":"6865.897000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576591200,"high":6776.59,"close":6751.77,"open":6773.48,"low":6688.68,"volume":3254.08,"support":6795.85,"resistence":"6876.349500000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576587600,"high":6885.98,"close":6773.64,"open":6879.22,"low":6725.44,"volume":5174.25,"support":6825.8,"resistence":"6884.774000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576584000,"high":6938.59,"close":6878.89,"open":6933.77,"low":6873,"volume":1403.85,"support":6802.58,"resistence":"6901.4420000000055","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576580400,"high":6939,"close":6933.76,"open":6887.52,"low":6875.5,"volume":1692.78,"support":6782.43,"resistence":"6912.375000000005","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576576800,"high":6905,"close":6887.52,"open":6881.98,"low":6881.92,"volume":933.318,"support":6769.3,"resistence":"6920.0975000000035","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576573200,"high":6887.54,"close":6881.98,"open":6879.43,"low":6875.5,"volume":1172.16,"support":6759.52,"resistence":"6931.4370000000035","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576569600,"high":6885,"close":6879.32,"open":6876.96,"low":6853.94,"volume":1285.38,"support":6752.51,"resistence":"6944.076500000003","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576566000,"high":6892.39,"close":6877.02,"open":6888.34,"low":6872,"volume":718.621,"support":6753.73,"resistence":"6955.517500000004","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576562400,"high":6898.02,"close":6888.34,"open":6889.31,"low":6885,"volume":597.425,"support":6759.49,"resistence":"6966.278500000003","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576558800,"high":6902.5,"close":6889.31,"open":6886.07,"low":6880,"volume":601.57,"support":6767.41,"resistence":"6974.967500000003","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576555200,"high":6891.97,"close":6886.09,"open":6886.34,"low":6864.83,"volume":1115.29,"support":6776.78,"resistence":"6983.3085000000065","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576551600,"high":6895.74,"close":6886.26,"open":6893.32,"low":6862,"volume":1439.87,"support":6787.78,"resistence":"6992.437000000007","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576548000,"high":6899.9,"close":6893.32,"open":6884.99,"low":6884.99,"volume":687.498,"support":6800.45,"resistence":"7001.436000000006","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576544400,"high":6899.89,"close":6885,"open":6896.52,"low":6883.42,"volume":656.421,"support":6813.3,"resistence":"7011.044500000005","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576540800,"high":6910,"close":6896.54,"open":6893.02,"low":6888,"volume":814.586,"support":6829.83,"resistence":"7020.3035000000045","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576537200,"high":6910,"close":6892.26,"open":6892.39,"low":6879.12,"volume":844.014,"support":6846.19,"resistence":"7029.021500000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576533600,"high":6903.05,"close":6892.32,"open":6887.2,"low":6880,"volume":438.919,"support":6865.63,"resistence":"7038.359000000001","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576530000,"high":6906.03,"close":6887.2,"open":6861.31,"low":6861.19,"volume":1208.13,"support":6887.88,"resistence":"7048.3315","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576526400,"high":6919.72,"close":6862.39,"open":6904.06,"low":6840,"volume":1821.98,"support":6915.35,"resistence":"7058.702500000002","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576522800,"high":6924.98,"close":6904.06,"open":6921.59,"low":6876.75,"volume":1585.2,"support":6957.99,"resistence":"7069.633000000002","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576519200,"high":7114,"close":6920.26,"open":7106.98,"low":6839.26,"volume":9768.76,"support":6996.69,"resistence":"7080.362000000003","created_at":"2019-12-24 00:07:27"},{"symbol":"btcusdt","timestamp":1576515600,"high":7109.76,"close":7107,"open":7098.14,"low":7093,"volume":399.846,"support":7090.25,"resistence":"7132.2734376032895","created_at":"2019-12-24 00:07:27"}]
     * BS : [{"symbol":"btcusdt","status":1,"timestamp":1577109001,"price":7589.9,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":1,"timestamp":1577000686,"price":7176.71,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":0,"timestamp":1576992185,"price":7130.84,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":1,"timestamp":1576947650,"price":7144.32,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":0,"timestamp":1576888036,"price":7160.48,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":1,"timestamp":1576827162,"price":7140,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":0,"timestamp":1576731711,"price":7127,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":1,"timestamp":1576677710,"price":6720,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":0,"timestamp":1576670993,"price":6601.24,"created_at":"2019-12-24 00:08:21"},{"symbol":"btcusdt","status":0,"timestamp":1576520962,"price":7049,"created_at":"2019-12-24 00:08:21"}]
     */

    private TradingPointBean trading_point;
    private KlineBean kline;
    private List<KlineListBean> kline_list;

    public TradingPointBean getTrading_point() {
        return trading_point;
    }

    public void setTrading_point(TradingPointBean trading_point) {
        this.trading_point = trading_point;
    }

    public KlineBean getKline() {
        return kline;
    }

    public void setKline(KlineBean kline) {
        this.kline = kline;
    }

    public List<KlineListBean> getKline_list() {
        return kline_list;
    }

    public void setKline_list(List<KlineListBean> kline_list) {
        this.kline_list = kline_list;
    }


    public static class TradingPointBean {
        /**
         * symbol : btcusdt
         * status : 1
         * timestamp : 1577109001
         * price : 7589.9
         * created_at : 2019-12-24 00:08:21
         */

        private String symbol;
        private int status;
        private String timestamp;
        private double price;
        private String created_at;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class KlineBean {
        /**
         * symbol : btcusdt
         * timestamp : 1577116800
         * high : 7585.66
         * close : 7583.62
         * open : 7584.34
         * low : 7583.62
         * volume : 49.4482
         * support : 7530.38
         * resistence : 7641.25556934686
         * created_at : 2019-12-24 00:07:27
         */

        private String symbol;
        private String timestamp;
        private double high;
        private double close;
        private double open;
        private double low;
        private double volume;
        private double support;
        private double resistence;
        private String created_at;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getSupport() {
            return support;
        }

        public void setSupport(double support) {
            this.support = support;
        }

        public double getResistence() {
            return resistence;
        }

        public void setResistence(double resistence) {
            this.resistence = resistence;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class KlineListBean {
        /**
         * symbol : btcusdt
         * timestamp : 1577116800
         * high : 7585.66
         * close : 7583.62
         * open : 7584.34
         * low : 7583.62
         * volume : 49.4482
         * support : 7530.38
         * resistence : 7641.25556934686
         * created_at : 2019-12-24 00:07:27
         */

        private String symbol;
        private String timestamp;
        private double high;
        private double close;
        private double open;
        private double low;
        private double volume;
        private double support;
        private String resistence;
        private String created_at;

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getSupport() {
            return support;
        }

        public void setSupport(double support) {
            this.support = support;
        }

        public String getResistence() {
            return resistence;
        }

        public void setResistence(String resistence) {
            this.resistence = resistence;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

}
