import {Component, Input, OnInit} from '@angular/core';

export class Machine {
  id: number;
  label: string;
  maxSpindleDrehzahl: number;
}

export class Material {
  id: number;
  label: string;
  eingriffswinkel: number;
  schnittgeschwindigkeit: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  @Input() id: string
  public _diameter: number = 10;
  public toothcount: number = 4;
  public _currentSchnittgeschwindigkeit: number;
  public _currentEingriffswinkel: number = 66.4;
  public zustellungAe: number = 3;
  public zustellungAe2: number;
  public machines: Machine[] = [];
  public _currentMachine: Machine;
  public materials: Material[] = [];
  public _currentMaterial: Material;
  public vorschub: number;
  public vorschub2: number;
  public vorschub3: number;
  public vorschub_fz: number = 0.05;
  public hm: number = 0.025;
  public vc: number;
  public vc2: number;
  public _currentMaxDrehzahl: number;
  public drehzahlOptimiert: number;
  public fzOptimiert: number;
  public fzOptimiert2: number;
  public ueberlappungXx: number;
  public ueberlappungXx2: number;
  public hm2: number;
  public eingriffswinkel2: number;


  public drehzahl: number;

  public mittlereSpanndicke: number = 0;
  public eingriffsflaeche: number = 0;

  applyStoredData() {
    let storedData = window.localStorage.getItem("gl.ing.mill.ToolboxData");
    if(storedData) {
      let data = JSON.parse(storedData);
      this.machines.filter(m =>{return (m.id === data.machine_id)}).forEach(m => {
        this._currentMachine = m;
      });
      this.materials.filter(m => {return (m.id === data.material_id)}).forEach(m => {
       this._currentMaterial = m;
      });
      this._currentSchnittgeschwindigkeit = data.schnitt_geschwindigkeit;
      this._currentMaxDrehzahl = data.max_drehzahl;
      this._diameter = data.diameter;
      this._currentEingriffswinkel = data.eingriffswinkel;
      this.toothcount = data.tooth_count;
      this.vorschub_fz = data.vorschub_fz;
      this.zustellungAe = data.zustellung_ae;
    }
  }

  ngOnInit() {
    this.initMachines();
    this.initMaterials();
    this.applyStoredData();
    this.recalc();
  }

  set currentMachine(machine: Machine) {
    this._currentMachine = machine;
    this._currentMaxDrehzahl = machine.maxSpindleDrehzahl;
    this.recalc();
  }

  get currentMaterial() {
    return this._currentMaterial;
  }

  set currentMaterial(material: Material) {
    this._currentMaterial = material;
    this._currentEingriffswinkel = material.eingriffswinkel;
    this._currentSchnittgeschwindigkeit = material.schnittgeschwindigkeit;
    this.recalc();
  }

  get currentMachine() {
    return this._currentMachine;
  }

  setCurrentMaxDrehzahl(dz: number) {

    this._currentMaxDrehzahl = dz;
    this.recalc();
  }

  public setDiameter(value: number) {
    console.log("diameter:", value);
    this._diameter = value;
    console.log("diameter:",this._diameter);
    this.recalc();
  }

  public setVorschub_fz(value: number) {
    console.log("vorschub_fz:",value);
    this.vorschub_fz = value;
    this.recalc();
  }

  public setSchnittgeschwindigkeit(value: number) {
    console.log("Schnittgeschwindigkeit:",value);
    this._currentSchnittgeschwindigkeit = value;
    this.recalc();
  }

  public setEingriffswinkel(value: number) {
    console.log("Eingriffswinkel:",value);
    this._currentEingriffswinkel = value;
    this.recalc();
  }

  public setZustellungAe(value: number) {
    console.log("ZustellungAe:",value);
    this.zustellungAe = value;
    this.recalc();
  }

  public setToothcount(value: number) {
    console.log("Zähnezahl:",value);
    this.toothcount = value;
    this.recalc();
  }


  recalc() {
    console.log("recalc", this._diameter);

    this.drehzahl = (this._currentSchnittgeschwindigkeit * 1000.0) / (this._diameter * Math.PI);
    this.drehzahl = Math.min(this.drehzahl, this._currentMaxDrehzahl);

    this.vorschub = this.drehzahl * this.toothcount * this.vorschub_fz;

    this.mittlereSpanndicke = this.vorschub_fz * Math.sqrt((this._diameter / 2) / this._diameter);
    this.eingriffsflaeche = this._diameter / 2 * this._diameter;
    // =(COS(BOGENMASS(B10))*(B1/2)-(B1/2))*-1
    this.zustellungAe2 = -1 * (Math.cos( this._currentEingriffswinkel * Math.PI / 180  ) * (this._diameter/2) - (this._diameter/2)) ;
    this.hm = this.vorschub_fz * Math.sqrt(this.zustellungAe2 / this._diameter);
    this.vc = this.mittlereSpanndicke / this.hm * this._currentSchnittgeschwindigkeit;

    this.drehzahlOptimiert = (this.vc * 1000)/(this._diameter * Math.PI);
    this.drehzahlOptimiert = Math.min(this.drehzahlOptimiert, this._currentMaxDrehzahl);

    this.fzOptimiert = this.mittlereSpanndicke / this.hm * this.vorschub_fz;
    this.vorschub2 = this.drehzahlOptimiert * this.toothcount * this.fzOptimiert;
    this.ueberlappungXx = this.zustellungAe / (this._diameter / 2);
    this.hm2 = this.vorschub_fz * Math.sqrt( this.zustellungAe / this._diameter);
    // =($B$7/$B$19)*(B21*1000/3.1415/$B$1)*$B$2*$B$3
    this.vc2 = this.mittlereSpanndicke / this.hm * this._currentSchnittgeschwindigkeit;
    this.vorschub3 = (this.mittlereSpanndicke / this.hm2) * (this.vc2 * 1000 / Math.PI / this._diameter)
          * this.vorschub_fz
          * this.toothcount;
    this.fzOptimiert2 = this.mittlereSpanndicke / this.hm2 * this.vorschub_fz;
    this.ueberlappungXx2 = this.zustellungAe2 / (this._diameter / 2);
    // =GRAD(ARCCOS((B1/2-B18)/(B1/2)))
    this.eingriffswinkel2 = (Math.PI / 180) * Math.acos((this._diameter / 2 - this.zustellungAe) / (this._diameter / 2));

    this.fzOptimiert = Math.round(1000 * this.fzOptimiert)/1000;
    this.vc2 = Math.round(1000 * this.vc2)/1000;
    this._currentEingriffswinkel = Math.round(1000 * this._currentEingriffswinkel)/1000;
    this.zustellungAe2 = Math.round(1000 * this.zustellungAe2)/1000;
    this.drehzahl = Math.round(1000 * this.drehzahl)/1000;
    this.vorschub = Math.round(1000 * this.vorschub)/1000;

    let data = {
      machine_id: this._currentMachine.id,
      material_id: this._currentMaterial.id,
      schnitt_geschwindigkeit: this._currentSchnittgeschwindigkeit,
      max_drehzahl: this._currentMaxDrehzahl,
      diameter: this._diameter,
      eingriffswinkel: this._currentEingriffswinkel,
      tooth_count: this.toothcount,
      vorschub_fz: this.vorschub_fz,
      zustellung_ae: this.zustellungAe
    };
    let dataAsString = JSON.stringify(data);
    window.localStorage.setItem("gl.ing.mill.ToolboxData",dataAsString);
  }

  initMachines() {

    let m = new Machine();
    m.label = "Keine";
    m.id = 0;
    m.maxSpindleDrehzahl = 0;
    this.machines.push(m);
    this._currentMachine = m;
    this._currentMaxDrehzahl = m.maxSpindleDrehzahl;

    m = new Machine();
    m.label = "Fehlmann, Picomax 56";
    m.id = 1;
    m.maxSpindleDrehzahl = 10000;
    this.machines.push(m);

    m = new Machine();
    m.label = "Deckel Maho, DMC-635V";
    m.id = 2;
    m.maxSpindleDrehzahl = 12000;
    this.machines.push(m);

    m = new Machine();
    m.label = "Hedelius, RS 80";
    m.id = 3;
    m.maxSpindleDrehzahl = 8000;
    this.machines.push(m);

    m = new Machine();
    m.label = "Deckel Maho, DMC 80U";
    m.id = 4;
    m.maxSpindleDrehzahl = 12000;
    this.machines.push(m);
  }


  initMaterials() {

    let m = new Material();
    m.label = "Keine";
    m.id = 0;
    m.eingriffswinkel = 50;
    m.schnittgeschwindigkeit = 150;
    this.materials.push(m);
    this._currentMaterial = m;
    this._currentEingriffswinkel = m.eingriffswinkel;
    this._currentSchnittgeschwindigkeit = m.schnittgeschwindigkeit;


    m = new Material();
    m.label = "Rostfrei, 1.4301";
    m.id = 1;
    m.eingriffswinkel = 40.5;
    m.schnittgeschwindigkeit = 80;
    this.materials.push(m);

    m = new Material();
    m.label = "Aluminium";
    m.id = 2;
    m.eingriffswinkel = 60;
    m.schnittgeschwindigkeit = 300;
    this.materials.push(m);

    m = new Material();
    m.label = "Stahl";
    m.id = 3;
    m.eingriffswinkel = 50;
    m.schnittgeschwindigkeit = 180;
    this.materials.push(m);
  }

}
