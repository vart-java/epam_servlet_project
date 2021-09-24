package com.artuhin.project.dto;

import com.artuhin.project.dto.models.*;
import com.artuhin.project.dto.models.MasterModel;
import com.artuhin.project.model.entity.Appointment;
import com.artuhin.project.model.entity.Procedure;
import com.artuhin.project.model.entity.User;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModelMaker {
    private static ModelMaker instance;

    private Map<String, Consumer<List<MasterAppModel>>> actionMap;

    private ModelMaker() {
        actionMap = new HashMap<>();
        actionMap.put("procedure", mastersRowDtos -> mastersRowDtos.sort(Comparator.comparing(MasterAppModel::getProcedureName)));
        actionMap.put("master", mastersRowDtos -> mastersRowDtos.sort(Comparator.comparing(MasterAppModel::getMasterName)));
        actionMap.put("rating", mastersRowDtos -> mastersRowDtos.sort(Comparator.comparingDouble(MasterAppModel::getMasterRating).reversed()));
    }

    public static synchronized ModelMaker getInstance() {
        if (instance == null) {
            instance = new ModelMaker();
        }
        return instance;
    }

    public List<FullAppModel> getFullAppDtos(List<Appointment> appointments, List<User> users, List<Procedure> procedures) {
        List<FullAppModel> fullAppModels = new ArrayList<>();
        for (Appointment app : appointments) {
            FullAppModel fullAppModel = new FullAppModel();
            fullAppModel.setId(app.getId());
            fullAppModel.setProcedureId(app.getProcedureId());
            fullAppModel.setProcedureName(procedures.stream().filter(p -> p.getId() == app.getProcedureId()).map(Procedure::getName).findAny().orElse(null));
            fullAppModel.setMasterId(app.getMasterId());
            fullAppModel.setMasterName(users.stream().filter(u -> u.getId() == app.getMasterId()).map(User::getLogin).map(l -> l.substring(0, l.indexOf('@'))).findAny().orElse(null));
            fullAppModel.setClientId(app.getClientId());
            fullAppModel.setClientName(users.stream().filter(u -> u.getId() == app.getClientId()).map(User::getLogin).map(l -> l.substring(0, l.indexOf('@'))).findAny().orElse(null));
            fullAppModel.setStartDateTime(app.getStartTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            fullAppModel.setEndDateTime(app.getStartTime().toLocalDateTime()
                    .plusSeconds(procedures.stream().filter(p -> p.getId() == app.getProcedureId())
                            .findAny().orElseThrow(() -> new RuntimeException("Procedure not found"))
                            .getDuration()).format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            fullAppModel.setStatus(app.getStatus());
            fullAppModels.add(fullAppModel);
        }
        return fullAppModels;
    }

    public List<FullAppModel> getFullAppDtosByMaster(long id, List<Appointment> appointments, List<User> users, List<Procedure> procedures) {
        return getFullAppDtos(appointments, users, procedures).stream().filter(d -> d.getMasterId() == id).collect(Collectors.toList());
    }

    public List<ClientAppModel> getClientAppDtos(long id, List<Appointment> appointments, List<User> users, List<Procedure> procedures) {
        appointments = appointments.stream().filter(a -> a.getClientId() == id).collect(Collectors.toList());
        List<ClientAppModel> clientAppDtos = new ArrayList<>();
        for (Appointment app : appointments) {
            ClientAppModel clientAppDto = new ClientAppModel();
            clientAppDto.setProcedureName(procedures.stream().filter(p -> p.getId() == app.getProcedureId()).map(Procedure::getName).findAny().orElse(null));
            clientAppDto.setMasterName(users.stream().filter(u -> u.getId() == app.getMasterId()).map(User::getLogin).map(l -> l.substring(0, l.indexOf('@'))).findAny().orElse(null));
            clientAppDto.setStartDateTime(app.getStartTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            clientAppDto.setEndDateTime(app.getStartTime().toLocalDateTime().plusSeconds(procedures.stream().filter(p -> p.getId() == app.getProcedureId()).findAny().orElseThrow(() -> new RuntimeException("Procedure not found")).getDuration()).format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            clientAppDto.setStatus(app.getStatus());
            clientAppDtos.add(clientAppDto);
        }
        return clientAppDtos;
    }

    public MasterModel getMasterDto(User user) {
        MasterModel masterModel = new MasterModel();
        masterModel.setId(user.getId());
        masterModel.setName(user.getSimpleName());
        masterModel.setRating(user.getRating());
        return masterModel;
    }


    public List<RateTableModel> getRateTableDtos(List<Procedure> procedures, List<User> users) {
        List<RateTableModel> rateTableModels = new ArrayList<>();
        for (Procedure pr : procedures) {
            RateTableModel rateTableModel = new RateTableModel();
            rateTableModel.setProcedureName(pr.getName());
            rateTableModel.setProcedureId(pr.getId());
            rateTableModel.setMasterDtoList(users.stream().filter(u -> u.getSkills().contains(pr))
                    .map(this::getMasterDto).sorted(Comparator.comparingDouble(MasterModel::getRating)
                            .reversed()).collect(Collectors.toList()));
            rateTableModels.add(rateTableModel);
        }
        return rateTableModels;
    }

    public List<MasterDailyScheduleModel> getMasterDailyScheduleDto(long id, int dayOfyear, List<Appointment> appointments, List<Procedure> procedures, List<User> users) {
        List<MasterDailyScheduleModel> masterDailyScheduleModels = new ArrayList<>();
        appointments = appointments.stream().filter(a -> a.getMasterId() == id).filter(a -> a.getStartTime().toLocalDateTime().getDayOfYear() == dayOfyear).collect(Collectors.toList());
        for (Appointment ap : appointments) {
            MasterDailyScheduleModel masterDailyScheduleModel = new MasterDailyScheduleModel();
            masterDailyScheduleModel.setAppointmentId(ap.getId());
            masterDailyScheduleModel.setProcedureName(procedures.stream().filter(p -> p.getId() == ap.getProcedureId()).map(Procedure::getName).findAny().orElse(null));
            masterDailyScheduleModel.setClientName(users.stream().filter(u -> u.getId() == ap.getClientId()).map(User::getSimpleName).findAny().orElse(null));
            masterDailyScheduleModel.setStartDateTime(ap.getStartTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            masterDailyScheduleModel.setEndDateTime(ap.getStartTime().toLocalDateTime().plusSeconds(procedures.stream().filter(p -> p.getId() == ap.getProcedureId()).findAny().orElseThrow(() -> new RuntimeException("Procedure not found")).getDuration()).format(DateTimeFormatter.ofPattern("HH:mm  |  dd.MM.yyyy")));
            masterDailyScheduleModel.setStatus(ap.getStatus());
            masterDailyScheduleModels.add(masterDailyScheduleModel);
        }
        return masterDailyScheduleModels;
    }

    public List<MasterAppModel> getMasterAppDtos(List<User> users) {
        List<MasterAppModel> masterAppModels = new ArrayList<>();
        for (User user : users) {
            for (Procedure procedure : user.getSkills()) {
                MasterAppModel masterAppModel = new MasterAppModel();
                masterAppModel.setId(user.getId());
                masterAppModel.setMasterName(user.getLogin().substring(0, user.getLogin().indexOf('@')));
                masterAppModel.setMasterRating(user.getRating());
                masterAppModel.setProcedureName(procedure.getName());
                masterAppModels.add(masterAppModel);
            }
        }
        return masterAppModels;
    }

    public List<MasterAppModel> sortMastersAppDtoList(String key, List<MasterAppModel> list) {
        actionMap.get(key).accept(list);
        return list;
    }

    public List<MasterAppModel> filterMastersAppDtoList(String keyName, String keyId, List<MasterAppModel> list) {
        List<MasterAppModel> returnList = new ArrayList<>();
        if (Integer.parseInt(keyId) == 2) {
            returnList = list.stream().filter(m -> m.getMasterName().equals(keyName)).collect(Collectors.toList());
        }
        if (Integer.parseInt(keyId) == 1) {
            returnList = list.stream().filter(m -> m.getProcedureName().equals(keyName)).collect(Collectors.toList());
        }
        if (Integer.parseInt(keyId) == 3) {
            returnList = list.stream().filter(m -> m.getMasterRating() == Double.parseDouble(keyName)).collect(Collectors.toList());
        }
        return returnList;
    }

    private FullUserModel getFullUserDto(User user) {
        Procedure procedure = new Procedure();
        procedure.setId(0);
        procedure.setName("empty");
        procedure.setDuration(0);
        Set<Procedure> set = new HashSet<>();
        set.add(procedure);
        FullUserModel fullUserModel = new FullUserModel();
        fullUserModel.setId(user.getId());
        fullUserModel.setLogin(user.getLogin());
        fullUserModel.setRole(user.getRole());
        fullUserModel.setSkills(user.getSkills());
        if (user.getSkills().isEmpty()){
            fullUserModel.setSkills(set);
        }
        fullUserModel.setRating(user.getRating());
        fullUserModel.setRecallCount(user.getRecallCount());
        return fullUserModel;
    }

    public List<FullUserModel> getFullUserDtoList(List<User> userList, int page) {
        int realPage = page-1;
        int end = realPage * 5 + 5;
        if (end > userList.size()) {
            end = userList.size();
        }
        return userList.stream().map(this::getFullUserDto).sorted(Comparator.comparingLong(FullUserModel::getId)).collect(Collectors.toList()).subList(realPage * 5, end);
    }

    public int getFullUserPaginationNumbers(List<User> userList) {
        return userList.size() / 5;
    }

    private ProcedureModel getProcedureDto(Procedure procedure) {
        ProcedureModel procedureModel = new ProcedureModel();
        procedureModel.setId(procedure.getId());
        procedureModel.setName(procedure.getName());
        return procedureModel;
    }

    public List<ProcedureModel> getProcedureDtos(List<Procedure> procedureList) {
        return procedureList.stream().map(this::getProcedureDto).collect(Collectors.toList());
    }

    public RecallModel getRecallDto(Appointment appointment, List<User> users, List<Procedure> procedures) {
        RecallModel recallModel = new RecallModel();
        recallModel.setMasterId(appointment.getMasterId());
        User master = users.stream().filter(u -> u.getId() == appointment.getMasterId()).findAny().orElseThrow(() -> new RuntimeException("Master not found"));
        recallModel.setMasterName(master.getSimpleName());
        recallModel.setMasterRating(master.getRating());
        recallModel.setProcedureName(procedures.stream().filter(p -> p.getId() == appointment.getProcedureId()).findAny().orElseThrow(() -> new RuntimeException("procedure not found")).getName());
        recallModel.setStatus(appointment.getStatus());
        recallModel.setAppointmentId(appointment.getId());
        return recallModel;
    }
}
